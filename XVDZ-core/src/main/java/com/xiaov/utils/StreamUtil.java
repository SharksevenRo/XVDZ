package com.xiaov.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;

public class StreamUtil {

	/**
	 * 把字节流转换成字符串
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String parserInputStream(InputStream is) throws IOException {
		// 把输入流转换成 字符串
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		// 内存流 写入读取的数据
		StringWriter sw = new StringWriter();

		String str = null;
		while ((str = br.readLine()) != null) {
			sw.write(str);
		}

		br.close();
		sw.close();
		return sw.toString();
	}

	/**
	 * 关闭流
	 * @param is
	 */
	public static void closeStream(InputStream is,OutputStream out) {

		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				is = null;
			}
		}
		
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				out = null;
			}
		}
	}

}
