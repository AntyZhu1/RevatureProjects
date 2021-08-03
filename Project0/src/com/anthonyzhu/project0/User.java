package com.anthonyzhu.project0;

import java.util.*;

public interface User {
	public String login(String username, String password);
	
	public static HashMap<String, String> employeeLoginInfo = new HashMap<>();
	public static HashMap<String, String> customerLoginInfo = new HashMap<>();
	public static HashMap<String, String> pendingAccounts = new HashMap<>();


}
