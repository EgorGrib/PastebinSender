import com.besaba.revonline.pastebinapi.Pastebin;
import com.besaba.revonline.pastebinapi.impl.factory.PastebinFactory;
import com.besaba.revonline.pastebinapi.paste.Paste;
import com.besaba.revonline.pastebinapi.paste.PasteBuilder;
import com.besaba.revonline.pastebinapi.paste.PasteExpire;
import com.besaba.revonline.pastebinapi.paste.PasteVisiblity;
import com.besaba.revonline.pastebinapi.response.Response;
import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;



public class PastebinAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {

        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        String selectedCode = editor.getSelectionModel().getSelectedText();

        PastebinFactory factory = new PastebinFactory();
        Pastebin pastebin = factory.createPastebin("YrIurl4greWI65Aoa3JrMcDqLOTkoRFD");


        final PasteBuilder pasteBuilder = factory.createPaste();
        pasteBuilder.setTitle("Paste from Idea");
        pasteBuilder.setRaw(selectedCode);
        pasteBuilder.setMachineFriendlyLanguage("java");
        pasteBuilder.setVisiblity(PasteVisiblity.Public);
        pasteBuilder.setExpire(PasteExpire.TenMinutes);
        final Paste paste = pasteBuilder.build();
        Messages.showMessageDialog("s", "a", Messages.getInformationIcon());
        /*
        final Response<String> postResult = pastebin.post(paste);

        if (postResult.hasError()) {
            System.out.println("Si è verificato un errore mentre postavo il paste: " + postResult.getError());
            return;
        }
        System.out.println("Paste pubblicato! Url: " + postResult.get());

        Messages.showMessageDialog("Paste published! URL: " + postResult.get(),
                "Pastebin Sender", Messages.getInformationIcon());
        BrowserUtil.browse(postResult.get());

*/
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
