## Video Demo
Please find a video demonstration of the app
here: https://drive.google.com/file/d/1a09BqJ3ZQrZltGFEkBr3WePy8UXzfkts/view?usp=sharing

## Features
* Pulls data from https://picsum.photos/v2/list
* Displays list of images and author name
* Loading and error screens
* Local Filtering of images by author
* Remembers the last selected filter after app restart
* Error screen allows user to retry API call if failed

## Extras
* Offline mode can be accessed from Error screen also
    * Only works if API call was previously successful, as the data needs to be written to local
      Room DB
    * Images are cached in memory and on disk using Coil
    * Offline mode can be exited using Refresh button on TopBar
* Lottie animations used for loading and error screens
* Some unit tests and UI tests added but ran out of time with these
* Sort by author or image ID (ascending or descending)
