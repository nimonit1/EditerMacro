package editormacro.keymacro;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class KeyMacroHandler {

	private Shell sh = null;

	private KeyMacroRecorder kmr = null;

	/**
	 *
	 */
	public KeyMacroHandler() {
		kmr = new KeyMacroRecorder();
	}

	public void start(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		if( sh 	!= null ){
			end();
		}
		sh = window.getShell();
		sh.addKeyListener(kmr);
	}


	public void end(){
		if( sh != null ){
			sh.removeKeyListener(kmr);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		sh = null;
		kmr = null;;
		super.finalize();
	}
}
