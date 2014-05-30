package com.legendshop.core.helper;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CharResponseWrapper extends HttpServletResponseWrapper
{
  protected CharArrayWriter charWriter = new CharArrayWriter();
  protected PrintWriter writer;
  protected boolean getOutputStreamCalled;
  protected boolean getWriterCalled;

  public CharResponseWrapper(HttpServletResponse paramHttpServletResponse)
  {
    super(paramHttpServletResponse);
  }

  public ServletOutputStream getOutputStream()
    throws IOException
  {
    if (this.getWriterCalled)
      throw new IllegalStateException("getWriter already called");
    this.getOutputStreamCalled = true;
    return super.getOutputStream();
  }

  public PrintWriter getWriter()
    throws IOException
  {
    if (this.writer != null)
      return this.writer;
    if (this.getOutputStreamCalled)
      throw new IllegalStateException("getOutputStream already called");
    this.getWriterCalled = true;
    this.writer = new PrintWriter(this.charWriter);
    return this.writer;
  }

  public String toString()
  {
    String str = null;
    if (this.writer != null)
      str = this.charWriter.toString();
    return str;
  }

  public char[] toCharArray()
  {
    return this.charWriter.toCharArray();
  }
}