package wrappers;

import java.io.IOException;
import java.io.ObjectInputStream;

import exceptions.ScanException;

public class ObjScanWrapper {
	private ObjectInputStream objScan;
	
	public ObjScanWrapper(ObjectInputStream objScan) {
		this.objScan = objScan;
	}
	
	public Object readObject() {
		try {
			return objScan.readObject();
		}
		catch(ClassNotFoundException | IOException e) {
			throw new ScanException();
		}
	}
}
