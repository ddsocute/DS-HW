import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Stack;

public class HtmlMatcher {
	private String urlStr;
	private String content;

	public HtmlMatcher(String urlStr) {
		this.urlStr = urlStr;
	}

	private String fetchContent() throws IOException {
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		StringBuilder retVal = new StringBuilder();

		String line;
		while ((line = br.readLine()) != null) {
			retVal.append(line).append("\n");
		}

		return retVal.toString();
	}

	public void match() throws IOException {
		if (content == null) {
			content = fetchContent();
		}

		/* Create a stack to store the tag */
		Stack<String> tagStack = new Stack<>();

		int indexOfOpen = 0;

		while ((indexOfOpen = content.indexOf("<", indexOfOpen)) != -1) {
			// 1. Get full tag. e.g. "<div id="abcdefg">","</a>","</div>"...
			int indexOfClose = content.indexOf(">", indexOfOpen);
			if (indexOfClose == -1) {
				break; // No closing '>', malformed tag
			}
			String fullTag = content.substring(indexOfOpen, indexOfClose + 1);

			// Extract tag name from fullTag. e.g. "div","/a","/div"...
			String tagName;
			int indexOfSpace;

			if ((indexOfSpace = fullTag.indexOf(" ")) == -1) {
				/* If there is no space in the fullTag (e.g. "<li>","</a>","</div>") */
				tagName = fullTag.substring(1, fullTag.length() - 1).trim();
			} else {
				/* If there are some space in the fullTag (e.g "<li id='theID'>") */
				tagName = fullTag.substring(1, indexOfSpace).trim();
			}

			tagName = tagName.toLowerCase();

			// Skip <!doctype> tag
			if (tagName.equalsIgnoreCase("!doctype")) {
				indexOfOpen = indexOfClose + 1;
				continue;
			}

			// Determine whether this tag is an open tag or close tag
			if (tagName.startsWith("/")) {
				// Close tag
				String closeTagName = tagName.substring(1).toLowerCase();

				// Check if stack is empty
				if (tagStack.isEmpty()) {
					System.out.println("False " + getStackString(tagStack));
					return;
				}

				// Compare to topmost tag in the stack
				String topMostTag = tagStack.peek();

				if (topMostTag.equals(closeTagName)) {
					// Match, pop from stack
					tagStack.pop();
				} else {
					// Not match
					System.out.println("False " + getStackString(tagStack));
					return;
				}
			} else {
				// Open tag

				// Handle self-closing tags (e.g., <br/>, <img/>)
				if (fullTag.endsWith("/>")) {
					// Self-closing tag, do nothing
				} else {
					// Push open tag onto stack
					tagStack.push(tagName);
				}
			}

			// Move the searching start point
			indexOfOpen = indexOfClose + 1;
		}

		/* After search and compare all the tags */
		if (!tagStack.isEmpty()) {
			// The stack is not empty, this means the tags are invalid
			System.out.println("False " + getStackString(tagStack));
		} else {
			// The stack is empty, all tags successfully matched.
			System.out.println("True");
		}
	}

	private String getStackString(Stack<String> stack) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < stack.size(); i++) {
			if (i > 0) {
				sb.append(" ");
			}
			sb.append(stack.get(i));
		}
		return sb.toString();
	}
}
