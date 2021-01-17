# JavaFX 16-ea+6 - possible bug in WebView

## Expected behavior

Chromium based web browsers render local and remote based MarkDown (MD) files as plain text, as shown in following images.

* Remotely stored markdown file: ![remote markdown rendering](doc/Edge_Chromium_Remote_MarkdownFile.png)
* Markdown file stored in local file system: ![locally stored MD file](doc/Edge_Chromium_Local_MarkdownFile.png)
* Generic TXT file stored in local file system: ![locally stored MD file](doc/Edge_Chromium_Local_TextFile.png)

## Observed behavior

* Local markdown in WebView - here no rendering happens, contents of file is not displayed: ![local markdown rendering](doc/WebView_Local_MarkdownFile.png)
* Local text file in WebView is rendered as expected: ![local markdown rendering](doc/WebView_Local_TextFile.png)
* Same with local html file in WebView, also rendered as expected: ![local markdown rendering](doc/WebView_Local_HtmlFile.png)
* Remote markdown in WebView: ![remote markdown rendering](doc/WebView_Remote_Markdown.png)

* Video demonstrating the issue: ![Video](doc/JavaFxWebViewIssue.mp4)
