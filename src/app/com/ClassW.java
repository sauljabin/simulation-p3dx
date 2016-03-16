/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>, Jorge Parra <thejorgemylio@gmail.com>.
 * 
 * This file is part of SimulationP3DX.
 * 
 * SimulationP3DX is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.com;

public class ClassW implements Comparable<ClassW> {

	private Class<?> clazz;

	public Class<?> getValue() {
		return clazz;
	}

	public void setValue(Class<?> clazz) {
		this.clazz = clazz;
	}

	public ClassW(Class<?> clazz) {
		this.clazz = clazz;
	}

	public ClassW() {

	}

	@Override
	public String toString() {
		return clazz.getSimpleName();
	}

	@Override
	public int compareTo(ClassW o) {
		return this.toString().compareTo(o.toString());
	}

}
