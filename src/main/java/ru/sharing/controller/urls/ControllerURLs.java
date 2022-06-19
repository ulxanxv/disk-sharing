package ru.sharing.controller.urls;

public final class ControllerURLs {

	public final static String SHARING_URL = "/sharing";
	public final static String DISKS_URL = SHARING_URL + "/disks";

	public final static String FREE_DISKS_URL = DISKS_URL + "/free";
	public final static String TAKEN_DISKS_URL = DISKS_URL + "/taken";
	public final static String DEBTORS_URL = DISKS_URL + "/debtors";

	public final static String TAKE_DISK_URL = DISKS_URL + "/take";
	public final static String RETURN_DISK_URL = DISKS_URL + "/return";

}
