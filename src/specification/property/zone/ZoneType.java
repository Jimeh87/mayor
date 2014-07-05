package specification.property.zone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public enum ZoneType {
	EMPTY("E", false), //SHOULD NOT BE USED IN ZoneSpecification
	RESIDENTIAL("R", false),
	COMMERCIAL("C", false),
	INDUSTRIAL("I", false),
	AGRICULTURAL("TODO", false), //A
	URBAN_SERVICE("TODO", true), //U
	;
	//NOTE: remember to add new ZoneType's to zoneTypeRestrictions
	
	private String paneId;
	private boolean autoZoned;
	private static final HashMap<ZoneType, ArrayList<ZoneType>> zoneTypeSupplierPurchaseRestrictions;

	private ZoneType(String paneId, boolean autoZoned) {
		this.paneId = paneId;
		this.autoZoned = autoZoned;
	}

	public String getPaneId() {
		return paneId;
	}

	public void setPaneId(String paneId) {
		this.paneId = paneId;
	}

	public boolean isAutoZoned() {
		return autoZoned;
	}

	public void setAutoZoned(boolean autoZoned) {
		this.autoZoned = autoZoned;
	}
	
	static {
		zoneTypeSupplierPurchaseRestrictions = new HashMap<ZoneType, ArrayList<ZoneType>>();
		//zoneTypeSupplierPurchaseRestrictions.put(ZoneType.EMPTY, new ArrayList<ZoneType>()); Should never get used, so commenting out
		zoneTypeSupplierPurchaseRestrictions.put(ZoneType.RESIDENTIAL, (ArrayList<ZoneType>) Arrays.asList(ZoneType.COMMERCIAL));
		zoneTypeSupplierPurchaseRestrictions.put(ZoneType.COMMERCIAL, (ArrayList<ZoneType>) Arrays.asList(ZoneType.COMMERCIAL, ZoneType.INDUSTRIAL));
		zoneTypeSupplierPurchaseRestrictions.put(ZoneType.INDUSTRIAL, (ArrayList<ZoneType>) Arrays.asList(ZoneType.INDUSTRIAL, ZoneType.AGRICULTURAL));
		zoneTypeSupplierPurchaseRestrictions.put(ZoneType.AGRICULTURAL, new ArrayList<ZoneType>());
		zoneTypeSupplierPurchaseRestrictions.put(ZoneType.URBAN_SERVICE, new ArrayList<ZoneType>());
	}
	
	public static List<ZoneType> getAvailableSuppliersForZoneTypeList(ZoneType zoneType) {
		List<ZoneType> suppliersForZoneType = zoneTypeSupplierPurchaseRestrictions.get(zoneType);
		if (suppliersForZoneType == null) {
			throw new IllegalStateException("Forgot to add ZoneType to zoneTypeSupplierPurchaseRestrictions... dummy");
		}
		return suppliersForZoneType;
	}
}
