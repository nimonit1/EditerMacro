package editermacro.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.StatusTextEditor;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class EditorMacroHandler extends AbstractHandler {

	public static final String EDITTOR_MACRO_CMD_COMMENT_OUT = "EditerMacro.commands.commentOut";

	/**
	 * The constructor.
	 */
	public EditorMacroHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
/*		MessageDialog.openInformation(
				window.getShell(),
				"EditerMacro",
				"Hello, Eclipse world");*/
/*		ISelection selection = window.getSelectionService().getSelection();
		if (selection instanceof ITextSelection) {
			ITextSelection ts  = (ITextSelection) selection;
			MessageDialog.openInformation(
					window.getShell(),
					"EditerMacro",
					ts.getText());
		}*/
		IEditorPart ep = window.getActivePage().getActiveEditor();
/*		if(ep instanceof TextEditor){
			TextEditor te = (TextEditor)ep;*/
		if(ep instanceof StatusTextEditor  ){
			StatusTextEditor ste = (StatusTextEditor)ep;
			IEditorInput editorInput = ste.getEditorInput();
			IDocument doc = ste.getDocumentProvider().getDocument(editorInput);
/*			MessageDialog.openInformation(
					window.getShell(),
					"EditerMacro",
					doc.get());*/
			if(event.getCommand().getId().equals(EDITTOR_MACRO_CMD_COMMENT_OUT)){
				ISelection selection = window.getSelectionService().getSelection();
				if (selection instanceof ITextSelection) {
					ITextSelection ts  = (ITextSelection) selection;
	/*				MessageDialog.openInformation(
							window.getShell(),
							"EditerMacro",
							String.valueOf(ts.getOffset())+":"+String.valueOf(ts.getLength())+":"+String.valueOf(ts.getStartLine())+":"+String.valueOf(ts.getEndLine()));*/
					try {
						if( ts.getStartLine() != ts.getEndLine() ){ /* if multi line */
							String strAry[] = ts.getText().split("\r\n");
							StringBuilder commendedString = new StringBuilder(""); /* 効率化 StringBuilder */
						    for (int i=0; i < strAry.length; i++) {
						    	/* コメントがある場合、先頭からコメント直前までコメントアウト */
						    	int startCommentIndex = strAry[i].indexOf("/*");
						    	if( -1 == startCommentIndex ){
							    	commendedString.append("/*" + strAry[i] + "*/\r\n");
						    	}
						    	else {
									StringBuilder commendedStringLine = new StringBuilder("/*"); /* 効率化 StringBuilder */
									commendedStringLine.append(strAry[i].replaceFirst("\\/\\*", "*//*"));
									commendedStringLine.append("\r\n");
									commendedString.append(commendedStringLine.toString());
						    	}
							}
							doc.replace(ts.getOffset(), ts.getLength(), commendedString.toString().substring(0, commendedString.length()-1));
						}
						else {
							doc.replace(ts.getOffset(), ts.getLength(), "/*" + ts.getText() + "*/");
						}
					} catch (BadLocationException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
}
