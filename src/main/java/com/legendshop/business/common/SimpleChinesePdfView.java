package com.legendshop.business.common;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import javax.servlet.jsp.JspException;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.displaytag.Messages;
import org.displaytag.exception.BaseNestableJspTagException;
import org.displaytag.exception.SeverityEnum;
import org.displaytag.export.BinaryExportView;
import org.displaytag.model.Column;
import org.displaytag.model.ColumnIterator;
import org.displaytag.model.HeaderCell;
import org.displaytag.model.Row;
import org.displaytag.model.RowIterator;
import org.displaytag.model.TableModel;

public class SimpleChinesePdfView
  implements BinaryExportView
{
  private TableModel model;
  private boolean exportFull;
  private boolean header;
  private boolean decorated;
  private Table tablePDF;
  private Font smallFont;

  public void setParameters(TableModel tableModel, boolean exportFullList, boolean includeHeader, boolean decorateValues)
  {
    this.model = tableModel;
    this.exportFull = exportFullList;
    this.header = includeHeader;
    this.decorated = decorateValues;
  }

  protected void initTable()
    throws BadElementException
  {
    this.tablePDF = new Table(this.model.getNumberOfColumns());
    this.tablePDF.setDefaultVerticalAlignment(4);
    this.tablePDF.setCellsFitPage(true);
    this.tablePDF.setWidth(100.0F);

    this.tablePDF.setPadding(2F);
    this.tablePDF.setSpacing(0F);

    this.smallFont = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H", 12.0F);
  }

  public String getMimeType()
  {
    return "application/pdf;charset=UTF-8";
  }

  protected void generatePDFTable()
    throws JspException, BadElementException
  {
    if (this.header)
      generateHeaders();

    this.tablePDF.endHeaders();
    generateRows();
  }

  public void doExport(OutputStream out)
    throws JspException
  {
    try
    {
      initTable();

      Document document = new Document(PageSize.A4.rotate(), 60.0F, 60.0F, 40.0F, 40.0F);
      document.addCreationDate();
      HeaderFooter footer = new HeaderFooter(new Phrase("", this.smallFont), true);
      footer.setBorder(0);
      footer.setAlignment(1);

      PdfWriter.getInstance(document, out);

      generatePDFTable();
      document.open();
      document.setFooter(footer);
      document.add(this.tablePDF);
      document.close();
    }
    catch (Exception e) {
      throw new PdfGenerationException(e);
    }
  }

  protected void generateHeaders()
    throws BadElementException
  {
    Iterator iterator = this.model.getHeaderCellList().iterator();

    while (iterator.hasNext()) {
      HeaderCell headerCell = (HeaderCell)iterator.next();

      String columnHeader = headerCell.getTitle();

      if (columnHeader == null) {
        columnHeader = StringUtils.capitalize(headerCell.getBeanPropertyName());
      }

      Cell hdrCell = getCell(columnHeader);
      hdrCell.setGrayFill(0.89999997615814209F);
      hdrCell.setHeader(true);
      this.tablePDF.addCell(hdrCell);
    }
  }

  protected void generateRows()
    throws JspException, BadElementException
  {
    RowIterator rowIterator = this.model.getRowIterator(this.exportFull);

    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();

      ColumnIterator columnIterator = row.getColumnIterator(this.model.getHeaderCellList());

      while (columnIterator.hasNext()) {
        Column column = columnIterator.nextColumn();

        Object value = column.getValue(this.decorated);

        Cell cell = getCell(ObjectUtils.toString(value));
        this.tablePDF.addCell(cell);
      }
    }
  }

  private Cell getCell(String value)
    throws BadElementException
  {
    Cell cell = new Cell(new Chunk(StringUtils.trimToEmpty(value), this.smallFont));
    cell.setVerticalAlignment(4);
    cell.setLeading(8.0F);
    return cell;
  }

  static class PdfGenerationException extends BaseNestableJspTagException
  {
    private static final long serialVersionUID = 899149338534L;

    public PdfGenerationException(Throwable cause)
    {
      super(SimpleChinesePdfView.class, Messages.getString("PdfView.errorexporting"), cause);
    }

    public SeverityEnum getSeverity()
    {
      return SeverityEnum.ERROR;
    }
  }
}