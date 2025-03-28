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


class Room (val name: String, val description: String) {
    var item: String? = null
    var north: Room? = null
    var south: Room? = null
    var east: Room? = null
    var west: Room? = null
}


/**
 * The application class (model)
 * This is the place where any application data should be
 * stored, plus any application logic functions
 */
class App() {
    // Data fields
    lateinit var currentLocation: Room

    init {
        val corridor = Room("Corridor", "A dark and dusty corridor")
        val emptyClass = Room("Empty Classroom", "A ...")
        val hall = Room("Hall", "A ...")
        val office = Room("Office", "A ...")

        corridor.south = office
        corridor.north = emptyClass
        emptyClass.south = corridor
        emptyClass.west = hall
        hall.east = emptyClass


        currentLocation = corridor

    }


    // Application logic functions
    fun updateLocation(newLocation: String) {
//        currentLocation = newLocation

    }
}


/**
 * Main UI window (view)
 * Defines the UI and responds to events
 * The app model should be passwd as an argument
 */
class MainWindow(val app: App) : JFrame(), ActionListener {

    // Fields to hold the UI elements
    private lateinit var PopUp: InstructionPopUp
    private lateinit var openButton: JButton

    private lateinit var locationLabel: JLabel
    private lateinit var locationDesc:  JLabel

    private lateinit var northButton: JButton
    private lateinit var eastButton:  JButton
    private lateinit var southButton: JButton
    private lateinit var westButton:  JButton

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
        val headerFont = Font(Font.SANS_SERIF, Font.BOLD,   36)
        val descFont =   Font(Font.SANS_SERIF, Font.ITALIC, 20)
        val buttonFont = Font(Font.SANS_SERIF, Font.PLAIN,  20)

        PopUp = InstructionPopUp()

        openButton = JButton("Instructions")
        openButton.bounds = Rectangle(425, 20, 150, 50)
        openButton.font = buttonFont
        openButton.addActionListener(this)
        openButton.background = Color.WHITE
        openButton.setForeground(Color.BLACK)
        add(openButton)

        locationLabel = JLabel("Location")
        locationLabel.horizontalAlignment = SwingConstants.CENTER
        locationLabel.bounds = Rectangle(50, 0, 500, 100)
        locationLabel.font = headerFont
        locationLabel.setForeground(Color.WHITE)
        add(locationLabel)

        locationDesc = JLabel("Description")
        locationDesc.bounds = Rectangle(50, 50, 500, 100)
        locationDesc.font = descFont
        locationDesc.setForeground(Color.WHITE)
        add(locationDesc)

// DIRECTION BUTTONS --------------------------------------------------------------

        northButton = JButton("North")
        northButton.bounds = Rectangle(250,300,100,50)
        northButton.font = buttonFont
        northButton.addActionListener(this)     // Handle any clicks
        northButton.background = Color.WHITE
        northButton.setForeground(Color.BLACK)
        add(northButton)

        eastButton = JButton("East")
        eastButton.bounds = Rectangle(350,350,100,50)
        eastButton.font = buttonFont
        eastButton.addActionListener(this)     // Handle any clicks
        eastButton.background = Color.WHITE
        eastButton.setForeground(Color.BLACK)
        add(eastButton)

        southButton = JButton("South")
        southButton.bounds = Rectangle(250,400,100,50)
        southButton.font = buttonFont
        southButton.addActionListener(this)     // Handle any clicks
        southButton.background = Color.WHITE
        southButton.setForeground(Color.BLACK)
        add(southButton)

        westButton = JButton("West")
        westButton.bounds = Rectangle(150,350,100,50)
        westButton.font = buttonFont
        westButton.addActionListener(this)     // Handle any clicks
        westButton.background = Color.WHITE
        westButton.setForeground(Color.BLACK)
        add(westButton)
    }


    /**
     * Update the UI controls based on the current state
     * of the application model
     */
    fun updateView() {
        locationLabel.text = app.currentLocation.name
        locationDesc.text = app.currentLocation.description

        // nameLabel.text = app.currentLocation.name
    }

    /**
     * Handle any UI events (e.g. button clicks)
     * Usually this involves updating the application model
     * then refreshing the UI view
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            openButton -> {
                PopUp.isVisible = true
            }
            northButton -> {
//                val newLocation = locationLabel.text
//                app.updateLocation(newLocation)
//                locationLabel.text = ""

                updateView()
            }
        }
    }
}

class InstructionPopUp(): JDialog() {

    init {
        configureWindow()
        addControls()
        setLocationRelativeTo(null)
    }

    private fun configureWindow() {
        title = "Instructions"
        contentPane.preferredSize = Dimension(400, 200)
        isResizable = false
        isModal = true
        layout = null
        contentPane.background = Color.BLACK
        pack()
    }
    private fun addControls() {
        val basefont = Font(Font.SANS_SERIF, Font.PLAIN, 16)

        val message = JLabel("<html><u>Find all the keys!</u>" +
                "<br><br>You are in an abandoned school." +
                "<br>Your goal is to navigate through it using the direction buttons, " +
                "and find all the keys throughout the school in order to escape. " +
                "<br>Good luck!")

        message.bounds = Rectangle(25,25,350,150)
        message.verticalAlignment = SwingConstants.TOP
        message.font = basefont
        message.setForeground(Color.WHITE)
        add(message)
    }
}