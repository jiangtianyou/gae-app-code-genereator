package com.app.modules.common.utils;

import com.app.modules.generator.entity.ClassInfo;

import java.io.IOException;

/**
 * code generate tool
 *
 * @author xuxueli 2018-04-25 16:29:58
 */
public class CodeGeneratorTool {


	public static ClassInfo processTableIntoClassInfo(String tableSql) throws IOException {
		return TableParseUtil.processTableIntoClassInfo(tableSql);
	}

}