package com.cambianombre.domain;

import java.io.Serializable;

public class Archivo implements Serializable {

	private static final long serialVersionUID = -5139606418901715038L;

	private String initialName;
	private String endName;
	private boolean directory;
	private String originSource;
	private String destinySource;
	private String extension;
	private String length;

	public String getInitialName() {
		return initialName;
	}

	public void setInitialName(String initialName) {
		this.initialName = initialName;
	}

	public String getEndName() {
		return endName;
	}

	public void setEndName(String endName) {
		this.endName = endName;
	}

	public boolean isDirectory() {
		return directory;
	}

	public void setDirectory(boolean directory) {
		this.directory = directory;
	}

	public String getOriginSource() {
		return originSource;
	}

	public void setOriginSource(String originSource) {
		this.originSource = originSource;
	}

	public String getDestinySource() {
		return destinySource;
	}

	public void setDestinySource(String destinySource) {
		this.destinySource = destinySource;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endName == null) ? 0 : endName.hashCode());
		result = prime * result + ((initialName == null) ? 0 : initialName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Archivo other = (Archivo) obj;
		if (endName == null) {
			if (other.endName != null)
				return false;
		} else if (!endName.equals(other.endName))
			return false;
		if (initialName == null) {
			if (other.initialName != null)
				return false;
		} else if (!initialName.equals(other.initialName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Archivo [initialName=" + initialName + ", endName=" + endName + ", directory=" + directory
				+ ", originSource=" + originSource + ", destinySource=" + destinySource + ", extension=" + extension
				+ ", length=" + length + "]\n";
	}

}
