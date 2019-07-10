FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Use local copy
BASE_URI = "git:///home/ajutras/dev/nxp/modules/WebKitForWayland;branch=end2end_sdp"
SRC_URI_append = " file://0001-incompatitable-types-fix.patch"
SRCREV = "${AUTOREV}"

# to avoid maximum call stack size range error
# validated in Hikey and iMX8M platforms
SRC_URI_remove = " \
            file://0001-Reduce-the-default-thread-stack-size-to-32KB.patch \
            file://0001-Reduce-stack-limits.patch \
           "

RDEPS_VIDEO_remove_imx = " gstreamer1.0-plugins-good-souphttpsrc"
RDEPS_VIDEO_append_imx = " gstreamer1.0-plugins-good-soup"

# removing commercial plugins 
RDEPS_EXTRA_remove = " \
                      gstreamer1.0-plugins-ugly-mpg123 \
                      gstreamer1.0-plugins-bad-hls \
                     "

# remove playready
PACKAGECONFIG_remove = "playready"

# enable opencdm
PACKAGECONFIG_append = " opencdm"
DEPENDS_append = " opencdm"
RDEPENDS_${PN} += "opencdm"
