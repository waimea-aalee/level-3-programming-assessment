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
        val corridor = Room("Corridor", "You find yourself in a dark and dusty corridor")
        val emptyClass = Room("Empty Classroom", "You are in a class room, with only a couple dusty knocked over desks and chairs")
        val hall = Room("Hall", "You are in a large hall which echoes with every footstep")
        val office = Room("Office", "You are in the office.")
        val computerRoom = Room("Computer Room", "Nothing but a bunch of cobweb covered computers")
        val computerRoomTwo = Room("Computer Room", "Nothing but a bunch of cobweb covered computers")
        val scienceLab = Room("Science Lab", "You are in the science lab, there's broken glasses all over the floor")
        val scienceLabTwo = Room("Science Lab", "You are in the science lab.")
        val mathClass = Room("Math Class", "You walk into the math class, there's dusty books all over the floor")
        val gym = Room("Gym", "You are in the gym, there's a score board that flickers lights and a few basketballs laying around")
        val storageRoom = Room("Storage Room", "You are in the storage room, it's full of run down gym and cleaning equipment")
        val courtyard = Room("Courtyard", "You are in the courtyard.")
        val library = Room("Library", "You are in a large library, there's books on the shelves and floor and there's knocked over ladders that reached the top shelves.")
        val englishRoom = Room("English Class", "You are in an English class, there's ripped up faded posters along the walls and a ruined projector.")
        val musicRoom = Room("Music Class", "You are in the music class, there's dusty instruments everywhere, they all look broken.")
        val closet  = Room("Closet", "You are in a dark, cramped closet with a singular flickering light dangling down from the ceiling.")
        val cafeteria = Room("Cafeteria", "A ...")
        val artRoom = Room("Art Room", "A ...")
        val auditorium = Room("Auditorium", "You are in the auditorium.")
        val theater = Room("Theater", "You are in the theater.")
        val languageClass = Room("Language Class", "You are in the language classroom.")
        val socialStudies = Room("Social Studies", "You are in the social studies class.")
        val classOne = Room("Random Class", "You are in the random class.")
        val classTwo = Room("Random Class", "You are in the random class.")
        val classThree = Room("Random Class", "You are in the random class.")
        val classFour = Room("Random Class", "You are in the random class.")
        val bathroom = Room("Bathroom", "You are in the bathroom.")







        corridor.north = emptyClass
        corridor.south = office
        office.south = cafeteria
        cafeteria.west = artRoom
        artRoom.east = cafeteria
        corridor.west = computerRoom
        emptyClass.south = corridor
        emptyClass.west = hall
        hall.east = emptyClass
        hall.west = scienceLab
        scienceLab.south = mathClass
        scienceLab.east = hall
        mathClass.north = scienceLab
        computerRoom.west = gym
        gym.east = computerRoom
        computerRoom.south = library
        library.south = closet
        closet.north = library
        closet.west = musicRoom
        library.west = courtyard
        courtyard.west = storageRoom
        storageRoom.south = englishRoom
        englishRoom.north = storageRoom
        englishRoom.east = musicRoom
        musicRoom.west = englishRoom
        musicRoom.east = closet
        library.north = computerRoom
        courtyard.east = library
        computerRoom.east = corridor
        office.north = corridor
        cafeteria.north = office
        artRoom.west = auditorium
        musicRoom.south = auditorium
        auditorium.east = artRoom
        office.west = languageClass
        languageClass.east = office
        hall.south = theater
        gym.north = theater
        theater.north = hall
        theater.south = gym
        languageClass.south = socialStudies
        socialStudies.north = languageClass
        gym.south = classOne
        courtyard.north = classOne
        classOne.north = gym
        classOne.south = courtyard
        artRoom.north = closet
        closet.south = artRoom
        mathClass.east = bathroom
        bathroom.west = mathClass
        bathroom.east = gym
        bathroom.north = scienceLabTwo
        scienceLabTwo.north = computerRoomTwo
        computerRoomTwo.south = scienceLabTwo
        gym.west = bathroom
        scienceLabTwo.south = bathroom


        currentLocation = corridor

    }

    fun moveNorth() {
        if (currentLocation.north == null) return

        currentLocation = currentLocation.north!!
    }

    fun moveEast() {
        if (currentLocation.east == null) return

        currentLocation = currentLocation.east!!
    }

    fun moveSouth() {
        if (currentLocation.south == null) return

        currentLocation = currentLocation.south!!
    }

    fun moveWest() {
        if (currentLocation.west == null) return

        currentLocation = currentLocation.west!!
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
        title = "Escape the School"
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

        openButton = JButton("Goal")
        openButton.bounds = Rectangle(455, 20, 120, 50)
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

        northButton.isEnabled = app.currentLocation.north != null
        eastButton.isEnabled = app.currentLocation.east != null
        southButton.isEnabled = app.currentLocation.south != null
        westButton.isEnabled = app.currentLocation.west != null
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
                app.moveNorth()
                updateView()
            }
            eastButton -> {
                app.moveEast()
                updateView()
            }
            southButton -> {
                app.moveSouth()
                updateView()
            }
            westButton -> {
                app.moveWest()
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
        title = "Goal"
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