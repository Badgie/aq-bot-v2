# Adventure Quest bot

## Game-specific setup
There are a couple of game-specific requirements 
that need to be met in order for the software to 
function correctly. These are listed below.
- Quick cast for spells is enabled in settings.
- Artix Launcher Client is used in full screen.
- XP bar is visible.

## Installation

### Dependencies
#### OpenJFX
To be able to run the software, you need a JavaFX runtime. 
Head over to [GluonHQ](https://gluonhq.com/products/javafx/)
to download. Extract it to an arbitrary location and add an 
environment variable `PATH_TO_FX` pointing to the `lib` folder.

### Windows
Navigate to environment variables in system properties, and add 
a new system variable named `PATH_TO_FX` pointing to
 `C:\path\to\openjfx\lib`

### UNIX
Open your `.bashrc` file and add the following line:

`export PATH_TO_FX="/path/to/openjfx/lib"`