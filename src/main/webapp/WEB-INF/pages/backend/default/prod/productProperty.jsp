<div class="pageFormContent nowrap">
	<dl>
		<dt>宝贝类型：</dt>
		<dd>
			<label><input type="radio" name="r1" />全新</label> <label><input
				type="radio" name="r1" />二手</label>
		</dd>
	</dl>

	<dl>
		<dt>品牌：</dt>
		<dd>
			<input type="hidden" name="orgLookup.id" value="${orgLookup.id}" />
			<input type="text" class="required" name="orgLookup.orgName" value=""
				suggestFields="orgNum,orgName"
				suggestUrl="demo/database/db_lookupSuggest.html"
				lookupGroup="orgLookup" /> <a class="btnLook"
				href="/buildpack/demo/database/dwzOrgLookup.html" lookupGroup="orgLookup">查找带回</a>
		</dd>
	</dl>

	<dl>
		<dt>袖长：</dt>
		<dd>
			<label><input type="checkbox" name="c1" value="1" />29444</label> <label><input
				type="checkbox" name="c1" value="2" />吊带裙</label> <label><input
				type="checkbox" name="c1" value="3" />无袖/背心裙</label>  <label><input
				type="checkbox" name="c1" value="5" />其他</label>
		</dd>
	</dl>
	
	<dl>
		<dt>领子A：</dt>
		<dd>
			<label><input type="checkbox" name="cp_20673" value="1" />立领</label> <label><input
				type="checkbox" name="cp_20673" value="2" />翻领</label> <label><input
				type="checkbox" name="cp_20673" value="3" />圆领</label> <label><input
				type="checkbox" name="cp_20673" value="5" />其他</label>
		</dd>
	</dl>

	<dl>
		<dt>款式细节SB：</dt>
		<dd>
			<input type="hidden" name="orgLookup.id" value="${orgLookup.id}" />
			<input type="text" class="required" name="orgLookup.orgName" value=""
				suggestFields="orgNum,orgName"
				suggestUrl="demo/database/db_lookupSuggest.html"
				lookupGroup="orgLookup" /> <a class="btnLook"
				href="/buildpack/demo/database/dwzOrgLookup.html" lookupGroup="orgLookup">查找带回</a>
		</dd>
	</dl>
	
	<dl>
		<dt>图案：</dt>
		<dd>
			<input type="hidden" name="orgLookup.id" value="${orgLookup.id}" />
			<input type="text" class="required" name="orgLookup.orgName" value=""
				suggestFields="orgNum,orgName"
				suggestUrl="demo/database/db_lookupSuggest.html"
				lookupGroup="orgLookup" /> <a class="btnLook"
				href="/buildpack/demo/database/dwzOrgLookup.html" lookupGroup="orgLookup">查找带回</a>
		</dd>
	</dl>
	
	<div>
		<table class="list nowrap itemDetail" addButton="添加自定义s属性" width="50%">
			<thead>
				<tr>
					<th type="text" name="items[#index#].itemString" size="12" fieldClass="required" fieldAttrs="{remote:'validate_remote.html', maxlength:10}">属性名</th>
					<th type="text" name="items[#index#].itemString" size="12" fieldClass="required" fieldAttrs="{remote:'validate_remote.html', maxlength:10}">属性值</th>
					<th type="del" width="60">操作</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
		
	
</div>
