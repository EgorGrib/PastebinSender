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

import java.text.SimpleDateFormat;
import java.util.Date;


public class PastebinAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        String selectedCode = editor.getSelectionModel().getSelectedText();

        if (selectedCode != null) {
            PastebinFactory factory = new PastebinFactory();
            Pastebin pastebin = factory.createPastebin("YrIurl4greWI65Aoa3JrMcDqLOTkoRFD");

            SimpleDateFormat simpleDate = new SimpleDateFormat("[HH:mm yyyy.MM.dd]");

            final PasteBuilder pasteBuilder = factory.createPaste().
                    setTitle("Paste from Idea " + simpleDate.format(new Date())).
                    setRaw(selectedCode).
                    setMachineFriendlyLanguage("java").
                    setVisiblity(PasteVisiblity.Public).
                    setExpire(PasteExpire.TenMinutes);

            final Paste paste = pasteBuilder.build();

            System.out.println("Paste from Idea " + simpleDate.format(new Date()));
            final Response<String> postResult = pastebin.post(paste);

            if (postResult.hasError()) {
                Messages.showMessageDialog("An error occurred while sending the paste: "
                        + postResult.getError(), "Pastebin Sender", Messages.getErrorIcon());
                return;
            }

            Messages.showMessageDialog("Paste published! Now you will follow the link to see your paste. URL: "
                    + postResult.get(), "Pastebin Sender", Messages.getInformationIcon());
            BrowserUtil.browse(postResult.get());

        } else {
            Messages.showMessageDialog("Selection is empty, you should select some text!", "Pastebin Sender",
                    Messages.getErrorIcon());
        }
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
