package nmt;

import java.util.ArrayList;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/", produces = "text/html;charset=UTF-8")
public class WebController {

	@GetMapping("")
	@ResponseBody
	public String homePage(@RequestParam(value = "tags", required = false) String tags, 
	@RequestParam(value = "tvnet", required = false) String tvnet,
	@RequestParam(value = "delfi", required = false) String delfi) {
		StringBuilder sb = new StringBuilder();
		if (tags != null) {

			sb.append("<form action=''>\n");
			// sb.append("not null");
			sb.append("Search: <input type='text' name='tags' value=" + tags + ">\n");
			sb.append("<input type='checkbox' name='tvnet' value='tvnet.lv' checked> tvnet.lv\n");
			sb.append("<input type='checkbox' name='delfi' value='delfi.lv' checked> delfi.lv\n");
			sb.append("<input type='submit' value='Find'><br/>\n");
			sb.append("<table>\n");

			List<String> sources = new ArrayList();
			sources.add(tvnet);
			sources.add(delfi);
			DBReader reader = new DBReader();

			for (Object[] o : reader.searchContent(tags, sources)) {
				String url = (String) o[0];
				String title = (String) o[1];
				String source = (String) o[2];
				sb.append("<tr>\n");
				sb.append("<td>" + source + "</td>\n");
				sb.append("<td><a href=\"" + url + "\" target=\"_blank\">" + title + "</a></td>\n");
				sb.append("</tr>\n");

			}
			sb.append("<a href='/'>Back</a>\n");
			return sb.toString();
		}

		sb.append("<form action=''>\n");
		sb.append("Search: <input type='text' name='tags' value=''>\n");
		sb.append("<input type='checkbox' name='tvnet' value='tvnet.lv' checked> tvnet.lv\n");
		sb.append("<input type='checkbox' name='delfi' value='delfi.lv' checked> delfi.lv\n");
		sb.append("<input type='submit' value='Find'></form><br/>\n");
		sb.append("<a href='/'>Back</a>\n");

		return sb.toString();
	}

}
