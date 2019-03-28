package web;

import java.util.ArrayList;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nmt.Content;
import nmt.DBReader;

@Controller
@RequestMapping(value = "/", produces = "text/html;charset=UTF-8")
public class WebController {

	@GetMapping("/")
	public String homePage(
			@RequestParam(value = "tags", required = false, defaultValue = "") String tags,
			@RequestParam(value = "tvnet", required = false) String tvnet,
			@RequestParam(value = "delfi", required = false) String delfi,
			@RequestParam(value = "rus.tvnet", required = false) String rusTvnet,
			@RequestParam(value = "rus.delfi", required = false) String rusDelfi,
			Model model) {

		if (tags != null) {
			List<String> sources = new ArrayList();
			sources.add(tvnet);
			sources.add(delfi);
			sources.add(rusTvnet);
			sources.add(rusDelfi);

			DBReader reader = new DBReader();
			List<Object[]> list = reader.searchContent(tags, sources);
			List<String> urlList = new ArrayList();
			List<String> titleList = new ArrayList();
			List<String> sourceList = new ArrayList();
			for (Object[] listItem : list) {
				urlList.add((String) listItem[0]);
				titleList.add((String) listItem[1]);
				sourceList.add((String) listItem[2]);
			}
			List<String[]> arrayList = new ArrayList();
			for (int i = 0; i < urlList.size(); i++) {
				String[] array = { urlList.get(i), titleList.get(i),
						sourceList.get(i) };
				arrayList.add(array);
			}

			model.addAttribute("tags", tags);
			model.addAttribute("arrayList", arrayList);

		}
		return "index";
	}

}
