package checkers;
import java.awt.*;
import java.applet.Applet;

public class Checkers extends Applet
{
    public Button newGameButton;
    public Label modeLabel;
    public CheckboxGroup Chkboxformultiplayer;
    public Checkbox playervsplayer;
    public Checkbox playervscpu;
    public CheckersPanel checkersPanel;
    public Label colorLabel;
    public CheckboxGroup CheckboxGrpInCheckers;
    public Checkbox redRadio;
    public Checkbox blackRadio;
    public Button undoButton;
    
    public void init()
    {
        setForeground(Color.black);
        setBackground(Color.lightGray);
        setFont(new Font("Dialog",Font.BOLD,12));
        setLayout(null);
        
        newGameButton = new Button("New Game");
        newGameButton.setFont(new Font("Dialog",Font.BOLD,14));

        modeLabel = new Label("Mode",Label.RIGHT);
        modeLabel.setFont(new Font("Dialog",Font.BOLD,12));

        Chkboxformultiplayer = new CheckboxGroup(); // CheckboxGroup in Checkers
	        playervsplayer = new Checkbox("Player Vs player",Chkboxformultiplayer,false);
	        playervsplayer.setBackground(Color.lightGray);
	        playervsplayer.setFont(new Font("Dialog",Font.BOLD,12));
	        playervscpu = new Checkbox("Player Vs CPU",Chkboxformultiplayer,false);
	        playervscpu.setBackground(Color.lightGray);
	        playervscpu.setFont(new Font("Dialog",Font.BOLD,12));

        checkersPanel = new CheckersPanel(this);

        colorLabel = new Label("Color",Label.RIGHT);
        colorLabel.setFont(new Font("Dialog",Font.BOLD,12));

        CheckboxGrpInCheckers = new CheckboxGroup(); // CheckboxGroup in Checkers
	        redRadio = new Checkbox("Red",CheckboxGrpInCheckers,false);
	        redRadio.setBackground(Color.lightGray);
	        redRadio.setFont(new Font("Dialog",Font.BOLD,12));
	        blackRadio = new Checkbox("Black",CheckboxGrpInCheckers,true);
	        blackRadio.setBackground(Color.lightGray);
	        blackRadio.setFont(new Font("Dialog",Font.BOLD,12));


        undoButton = new Button("Undo");
        undoButton.setFont(new Font("Dialog",Font.BOLD,12));

        add(newGameButton);
        add(modeLabel);
        add(playervsplayer);
        add(playervscpu);
        add(checkersPanel);        
        add(colorLabel);
        add(blackRadio);
        add(redRadio);
        add(undoButton);

        InitialPositionSet();
    }

    public void start(){}

    public void stop(){}
    
    public void destroy(){}

    public void paint(Graphics g){}

    @SuppressWarnings("deprecation")
	public void InitialPositionSet()
    {
    	resize(490,530);
        checkersPanel.reshape(0,0,480,480);
        newGameButton.reshape(5,485,115,35);
        modeLabel.reshape(130,490,50,35);        
        playervsplayer.reshape(190,490,160,15);
        playervscpu.reshape(190,512,160,15);
        
        
        colorLabel.reshape(46,505,43,19);
        redRadio.reshape(95,505,59,16);
        blackRadio.reshape(95,525,67,16);
        undoButton.reshape(178,505,99,34);       
    }

    @SuppressWarnings("deprecation")
	public boolean handleEvent(Event evt)
    {
        if (evt.id == Event.WINDOW_DESTROY && evt.target == this) Checkers_WindowDestroy(evt.target);
        else if (evt.id == Event.ACTION_EVENT && evt.target == newGameButton) newGameButton_Action(evt.target);
        else if (evt.id == Event.ACTION_EVENT && evt.target == redRadio) redRadio_Action(evt.target);
        else if (evt.id == Event.ACTION_EVENT && evt.target == blackRadio) blackRadio_Action(evt.target);
        else if (evt.id == Event.ACTION_EVENT && evt.target == undoButton) undoButton_Action(evt.target);
        return super.handleEvent(evt);
    }

    public void Checkers_WindowDestroy(Object target)
    {
        System.exit(0);
    }

    public void newGameButton_Action(Object target)
    {
        checkersPanel.newGame();
    }

    public void redRadio_Action(Object target)
    {
        //checkersPanel.setColor("red");
    }

    public void blackRadio_Action(Object target)
    {
        //checkersPanel.setColor("black");
    }

    public void undoButton_Action(Object target)
    {
        //checkersPanel.undo();
    }    
}