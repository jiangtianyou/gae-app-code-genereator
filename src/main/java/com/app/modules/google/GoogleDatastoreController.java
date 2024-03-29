package com.app.modules.google;

import com.google.appengine.api.datastore.*;
import com.google.common.collect.ImmutableList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("datastore")
public class GoogleDatastoreController {
	static final String IS_POPULATED_ENTITY = "IsPopulated";
	static final String IS_POPULATED_KEY_NAME = "is-populated";
	private static final String PERSON_ENTITY = "Person";
	private static final String NAME_PROPERTY = "name";
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	private static final ImmutableList<String> US_PRESIDENTS =
			ImmutableList.<String>builder()
					.add("George Washington")
					.add("John Adams")
					.add("Thomas Jefferson")
					.add("James Madison")
					.add("James Monroe")
					.add("John Quincy Adams")
					.add("Andrew Jackson")
					.add("Martin Van Buren")
					.add("William Henry Harrison")
					.add("John Tyler")
					.add("James K. Polk")
					.add("Zachary Taylor")
					.add("Millard Fillmore")
					.add("Franklin Pierce")
					.add("James Buchanan")
					.add("Abraham Lincoln")
					.add("Andrew Johnson")
					.add("Ulysses S. Grant")
					.add("Rutherford B. Hayes")
					.add("James A. Garfield")
					.add("Chester A. Arthur")
					.add("Grover Cleveland")
					.add("Benjamin Harrison")
					.add("Grover Cleveland")
					.add("William McKinley")
					.add("Theodore Roosevelt")
					.add("William Howard Taft")
					.add("Woodrow Wilson")
					.add("Warren G. Harding")
					.add("Calvin Coolidge")
					.add("Herbert Hoover")
					.add("Franklin D. Roosevelt")
					.add("Harry S. Truman")
					.add("Dwight D. Eisenhower")
					.add("John F. Kennedy")
					.add("Lyndon B. Johnson")
					.add("Richard Nixon")
					.add("Gerald Ford")
					.add("Jimmy Carter")
					.add("Ronald Reagan")
					.add("George H. W. Bush")
					.add("Bill Clinton")
					.add("George W. Bush")
					.add("Barack Obama")
					.build();


	@RequestMapping("set")
	@ResponseBody
	public String  addEnity(){
		Key isPopulatedKey = KeyFactory.createKey(IS_POPULATED_ENTITY, IS_POPULATED_KEY_NAME);
		boolean isAlreadyPopulated;
		try {
			datastore.get(isPopulatedKey);
			isAlreadyPopulated = true;
		} catch (EntityNotFoundException expected) {
			isAlreadyPopulated = false;
		}
		if (isAlreadyPopulated) {
		}

		ImmutableList.Builder<Entity> people = ImmutableList.builder();
		for (String name : US_PRESIDENTS) {
			Entity person = new Entity(PERSON_ENTITY);
			person.setProperty(NAME_PROPERTY, name);
			people.add(person);
		}
		datastore.put(people.build());
		datastore.put(new Entity(isPopulatedKey));

		return "ok";
	}

	@RequestMapping("get")
	@ResponseBody
	public String  get(){
		FetchOptions fetchOptions = FetchOptions.Builder.withLimit(100);
		Query q = new Query("WarningPoint");
		PreparedQuery pq = datastore.prepare(q);

		QueryResultList<Entity> results;
//		try {
//			results = pq.asQueryResultList(fetchOptions);
//			Entity entity = results.get(0);
//			Long CYB_MIN  = (Long) entity.getProperty("CYB_MIN");
//			Long CYB_MAX  = (Long) entity.getProperty("CYB_MAX");
//			Long SZ_MIN  = (Long) entity.getProperty("SZ_MIN");
//			Long SZ_MAX  = (Long) entity.getProperty("SZ_MAX");
//			Boolean IS_CLOSE = (Boolean) entity.getProperty("IS_CLOSE");
//			return CYB_MIN + "| "+CYB_MAX + "| "+SZ_MIN + "| "+SZ_MAX + "| " +IS_CLOSE;
//		} catch (IllegalArgumentException e) {
//			return "fail";
//		}
		return null;
	}



}
