#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

DESCRIPTION = "Open Content Decryption Module"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea83f8bc099c40bde8c4f2441a6eb40b"

FILESEXTRAPATHS_prepend := "${THISDIR}:"

SRC_URI = "git://github.com/linaro-mmwg/open-content-decryption-module-cdmi.git;protocol=https"
SRCREV = "35e6436c899fed053ab4aaaf8ba9fc2a42bc5f7f"

S = "${WORKDIR}/git"

EXTRA_OECONF_append = "${@bb.utils.contains('MACHINE_FEATURES', 'optee', '--enable-aes-ta', '', d)} "
EXTRA_OECONF_append = " --enable-debug "
EXTRA_OECONF_append = " --enable-sdp "

# * --enable-playready : Enables support for Playready CDMI.
#
# * --enable-sdp : Enable support for Secure Data Path in CDMI.
#
# * --enable-debug : Builds OCDM with debug symbols and verbose logging.

# Only ClearKey implementation depends on ssl:
DEPENDS_append = " \
    ${@bb.utils.contains('PACKAGECONFIG','--enable-playready','','openssl',d)} \
    rpcbind \
    ${@bb.utils.contains('MACHINE_FEATURES','optee','optee-aes-decryptor','',d)} \
"

inherit autotools
