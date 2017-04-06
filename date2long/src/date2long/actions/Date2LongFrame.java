package date2long.actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Date2LongFrame {
  private Shell shell;
  private Shell window;
  private Display display;
  private static final int WIDTH = 350;
  private static final int HEIGHT = 160;
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  Date2LongFrame(Shell shell) {
    this.shell = shell;
    this.window = shell.getShell();
    this.display = shell.getDisplay();
  }

  public void show() {
    shell.setText("日期毫秒转换");
    Rectangle rect = window.getBounds();
    shell.setBounds(rect.width / 2 - 100, rect.height / 2 - 100, WIDTH, HEIGHT);
    Font font = new Font(display, "Arial", 14, SWT.BOLD | SWT.ITALIC);

    GridLayout gridLayOut = new GridLayout();
    gridLayOut.numColumns = 2;
    gridLayOut.verticalSpacing = 8;
    shell.setLayout(gridLayOut);
    //test
    Label labMillis = new Label(shell, SWT.LEFT);
    labMillis.setText("毫秒数");
    labMillis.setFont(font);

    Text textLong = new Text(shell, SWT.LEFT);
    textLong.setFont(font);
    textLong.setText(System.currentTimeMillis() + "");

    Label labelDate = new Label(shell, SWT.LEFT);
    labelDate.setText("日期");
    labelDate.setFont(font);

    Text textDate = new Text(shell, SWT.LEFT);
    textDate.setFont(font);
    textDate.setText(DATE_FORMAT.format(new Date()));

    Button btn2long = new Button(shell, SWT.LEFT);
    btn2long.setText("转为毫秒");
    btn2long.setFont(font);

    btn2long.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        try {
          String strDate = textDate.getText();
          long longVal = DATE_FORMAT.parse(strDate).getTime();
          textLong.setText(longVal + "");
        } catch (Exception e) {
          MessageDialog.openInformation(shell, "出错了", "出错了" + e.getMessage());
        }
      }
    });

    Button btn2date = new Button(shell, SWT.CENTER);
    btn2date.setText("转为日期");
    btn2date.setFont(font);
    btn2date.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        try {
          String strLong = textLong.getText();
          String strDate = DATE_FORMAT.format(new Date(Long.parseLong(strLong)));
          textDate.setText(strDate);
        } catch (Exception e) {
          MessageDialog.openInformation(shell, "出错了", "出错了" + e.getMessage());
        }
      }
    });

    shell.open();
  }
}