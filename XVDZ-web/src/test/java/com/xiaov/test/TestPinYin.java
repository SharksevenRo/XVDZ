package com.xiaov.test;

import com.xiaov.utils.Pinyin4jUtil;

public class TestPinYin {
	public static void main(String[] args) {
		String str="女孩子";
		String spellNoneTone = Pinyin4jUtil.spellNoneTone(str);
		System.out.println(spellNoneTone);
		System.out.println(spellNoneTone.replace("ü", ""));
	}
}
