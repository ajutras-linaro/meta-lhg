SUMMARY = "Open Content Decryption Module for WPE"
HOMEPAGE = "https://www.fokus.fraunhofer.de/en"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea83f8bc099c40bde8c4f2441a6eb40b"

DEPENDS = "glib-2.0"
DEPENDS_append_libc-musl = " libtirpc"
CPPFLAGS_append_libc-musl = " -I${STAGING_INCDIR}/tirpc"
CXXFLAGS_append_libc-musl = " -I${STAGING_INCDIR}/tirpc"
CFLAGS_append_libc-musl = " -I${STAGING_INCDIR}/tirpc"
LDFLAGS_append_libc-musl = " -ltirpc"

SRCREV = "02bef6abf867aab5a918c9544e678c6f1b492045"

PV = "1.0.gitr${SRCPV}"
S = "${WORKDIR}/git"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "git://github.com/linaro-home/open-content-decryption-module;branch=chromium-53.0.2785.143 \
           file://0001-ion_allocator_helper-Support-new-ION-API.patch \
           file://0002-ion_allocator_helper-HACK-Copy-ion.h-locally.patch \
           file://0001-Allow-to-support-end-to-end-SDP.patch \
           file://0001-Protect-against-concurent-access-to-the-media-engine.patch \
           file://0001-Handle-non-secure-content-simultanuously-with-secure.patch \
           file://0002-Hack-Do-not-send-file-descriptor-for-non-secure-cont.patch"

CXXFLAGS += "-Wno-narrowing"

do_compile_prepend() {
        mkdir -p ${S}/src/browser/wpe/test/bin
}

do_install(){
        install -d ${D}${includedir}
        install -d ${D}${libdir}
        install -m 0755 ${B}/src/browser/wpe/lib/libocdm.so ${D}${libdir}
        install -m 0755 ${B}/src/browser/wpe/include/*.h ${D}${includedir}
}

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"
FILES_${PN} += "${includedir}/*.h"

INSANE_SKIP_${PN} = "ldflags"

PARALLEL_MAKE = ""
