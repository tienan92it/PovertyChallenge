/*
 Project name: GAYM
 File name   : 
 Author      : DuongNX
 Description : 
 Created date: 02/10/2014
 Version     : 1.00
 --------------------------------------------------------------
 Copyright (C) 2014 GAYM. All Rights Reserved.
 --------------------------------------------------------------
 */
package com.appable.povertychallenge.interfaces;

import java.util.ArrayList;

public interface IList<E> {

	public void addEntry(E entry);

	public void removeEntry(E entry);
	
	public void removeEntry(int position);

	public void addEntries(ArrayList<E> entries);

	public ArrayList<E> getEntries();

	public void clear();

}
