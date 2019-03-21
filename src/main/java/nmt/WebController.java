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
	public String homePage(@RequestParam(value = "tags", required = false) String tags) {
		StringBuilder sb = new StringBuilder();
		if (tags != null) {

			sb.append("<form action=''>\n");
			// sb.append("not null");
			sb.append("Search: <input type='text' name='tags' value=''>\n");
			sb.append("<input type='submit' value='Find'><br/>\n");
			sb.append("<table>\n");

			DBReader reader = new DBReader();

			for (Object[] o : reader.searchContent(tags)) {
				String url = (String) o[0];
				String title = (String) o[1];
				String source = (String) o[2];
				sb.append("<tr>\n");
				sb.append("<td>" + source + "</td>\n");
				// sb.append("<td>" + title + "</td>\n");
				// sb.append("<td>" + url + "</td>\n");
				sb.append("<td><a href=\"" + url + "\" target=\"_blank\">" + title + "</a></td>\n");
				sb.append("</tr>\n");

			}
			// sb.append("true<br/>\n");
			sb.append("<a href='/'>Back</a>\n");
			// response.setStatus(HttpServletResponse.SC_OK);
			return sb.toString();
		}

		sb.append("<form action=''>\n");
		// sb.append("null");
		sb.append("Search: <input type='text' name='tags' value=''>\n");
		sb.append("<input type='submit' value='Find'></form><br/>\n");
		sb.append("<a href='/'>Back</a>\n");
		// response.setStatus(HttpServletResponse.SC_OK);

		return sb.toString();
	}

}
