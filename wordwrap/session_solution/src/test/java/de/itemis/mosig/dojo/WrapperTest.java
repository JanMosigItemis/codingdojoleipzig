package de.itemis.mosig.dojo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.itemis.mosig.dojo.Wrapper;

public class WrapperTest {

	@Test
	public void wrapAfterEachChar(){
		Wrapper wrapper = new Wrapper();
		String erg = wrapper.wrap("aaaa", 1);
		assertThat(erg, is("a\na\na\na"));
	}
	
	@Test
	public void wrapAfterOneChar(){
		Wrapper wrapper = new Wrapper();
		String erg = wrapper.wrap("a", 1);
		assertThat(erg, is("a"));
	}
	
	@Test
	public void wrapEachCharWithColTwo(){
		Wrapper wrapper = new Wrapper();
		String erg = wrapper.wrap("aaa", 2);
		assertThat(erg, is("aa\na"));
	}
	
	@Test
	public void wrapAfterEachCharWithB(){
		Wrapper wrapper = new Wrapper();
		String erg = wrapper.wrap("bbbb", 1);
		assertThat(erg, is("b\nb\nb\nb"));
	}
	
	
	
}
