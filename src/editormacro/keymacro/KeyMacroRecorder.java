package editormacro.keymacro;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;

public class KeyMacroRecorder implements KeyListener {

	public KeyMacroRecorder() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void keyPressed(KeyEvent e) {
        //キーボードが押されたときの処理
        System.out.println("Pressed : ch " + Integer.toString(e.character));
        System.out.println("keyCode "+ Integer.toString(e.keyCode,16));
        System.out.println("stateMask "+ Integer.toString(e.stateMask,16));
	}

	@Override
	public void keyReleased(KeyEvent e) {
        //キーボードが離されたときの処理
        System.out.println("Released : ch " + Integer.toString(e.character));
        System.out.println("keyCode "+ Integer.toString(e.keyCode,16));
        System.out.println("stateMask "+ Integer.toString(e.stateMask,16));

	}

}
