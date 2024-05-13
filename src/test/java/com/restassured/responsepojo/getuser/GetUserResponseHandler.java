package com.restassured.responsepojo.getuser;

import java.lang.reflect.Type;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class GetUserResponseHandler
{
	public List<GetUser> getGetUserObject(String jsonString)
	{
		try {
			Type listType = new TypeToken<List<GetUser>>() {}.getType();
			Gson gson = new Gson();
			return gson.fromJson(jsonString, listType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
