/**
 * =====================================================================
 * Programming Project for NCEA Level 3, Standard 91906
 * ---------------------------------------------------------------------
 * Project Name:   PROJECT NAME HERE
 * Project Author: Azaria Lee
 * GitHub Repo:    https://github.com/waimea-aalee/level-3-programming-assessment
 * ---------------------------------------------------------------------
 * Notes:
 * PROJECT NOTES HERE
 * This game involves...
 * =====================================================================
 */



import com.formdev.flatlaf.FlatDarkLaf
import java.awt.*
import java.awt.event.*
import javax.swing.*


/**
 * Launch the application
 */
fun main() {
    FlatDarkLaf.setup()     // Flat, dark look-and-feel
    val app = App()         // Create the app model
    MainWindow(app)         // Create and show the UI, using the app model
}


/**
 * The application class (model)
 * This is the place where any application data should be
 * stored, plus any application logic functions
 */
class App() {
    // Constants defining any key values
    val MAX_CLICKS = 10

    // Data fields
    var clicks = 0

    // Application logic functions
    fun updateClickCount() {
        clicks++
        if (clicks > MAX_CLICKS) clicks = MAX_CLICKS
    }
}


/**
 * Main UI window (view)
 * Defines the UI and responds to events
 * The app model should be passwd as an argument
 */
class MainWindow(val app: App) : JFrame(), ActionListener {

    // Fields to hold the UI elements
    private lateinit var clicksLabel: JLabel
    private lateinit var locationDesc: JLabel

    private lateinit var northButton: JButton
    private lateinit var eastButton: JButton
    private lateinit var southButton: JButton
    private lateinit var westButton: JButton

    /**
     * Configure the UI and display it
     */
    init {
        configureWindow()               // Configure the window
        addControls()                   // Build the UI

        setLocationRelativeTo(null)     // Centre the window
        isVisible = true                // Make it visible

        updateView()                    // Initialise the UI
    }

    /**
     * Configure the main window
     */
    private fun configureWindow() {
        title = "Kotlin Swing GUI Demo"
        contentPane.preferredSize = Dimension(600, 500)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        layout = null
        contentPane.background = Color.BLACK

        pack()
    }

    /**
     * Populate the UI with UI controls
     */
    private fun addControls() {
        val headerFont = Font(Font.SANS_SERIF, Font.BOLD, 36)
        val descFont = Font(Font.SANS_SERIF, Font.ITALIC, 20)
        val buttonFont = Font(Font.SANS_SERIF, Font.PLAIN, 20)

        clicksLabel = JLabel("Location")
        clicksLabel.horizontalAlignment = SwingConstants.CENTER
        clicksLabel.bounds = Rectangle(50, 0, 500, 100)
        clicksLabel.font = headerFont
        add(clicksLabel)

        locationDesc = JLabel("Description")
        locationDesc.bounds = Rectangle(50, 50, 500, 100)
        locationDesc.font = descFont
        add(locationDesc)

        northButton = JButton("North")
        northButton.bounds = Rectangle(250,300,100,50)
        northButton.font = buttonFont
        northButton.addActionListener(this)     // Handle any clicks
        add(northButton)

        eastButton = JButton("East")
        eastButton.bounds = Rectangle(350,350,100,50)
        eastButton.font = buttonFont
        eastButton.addActionListener(this)     // Handle any clicks
        add(eastButton)

        southButton = JButton("South")
        southButton.bounds = Rectangle(250,400,100,50)
        southButton.font = buttonFont
        southButton.addActionListener(this)     // Handle any clicks
        add(southButton)

        westButton = JButton("West")
        westButton.bounds = Rectangle(150,350,100,50)
        westButton.font = buttonFont
        westButton.addActionListener(this)     // Handle any clicks
        add(westButton)
    }


    /**
     * Update the UI controls based on the current state
     * of the application model
     */
    fun updateView() {

    }

    /**
     * Handle any UI events (e.g. button clicks)
     * Usually this involves updating the application model
     * then refreshing the UI view
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            northButton -> {
                app.updateClickCount()
                updateView()
            }
        }
    }

}

