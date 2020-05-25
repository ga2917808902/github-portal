package com.portal;

import java.util.Map;
import java.util.TreeMap;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ConfigPage<T> {

	public Map<String, Integer> pagination(Page<T> page) {
		Map<String, Integer> map = new TreeMap<String, Integer>();
		int totalPages = page.getTotalPages();
		if (totalPages > 0) {
			int current = page.getNumber() + 1;
			int begin = Math.max(1, current - 5);
			int end = Math.min(begin + 5, totalPages);
			map.put("begin", begin);
			map.put("end", end);
			map.put("last", totalPages);
			map.put("current", current);
		}

		return map;
	}

	/**
	 * clear cache
	 * 
	 * @param page
	 * @return
	 */

//	@CacheEvict("recentlyBook")
//	public void flushCache() {System.out.println("CLEAR");
//	}
}
