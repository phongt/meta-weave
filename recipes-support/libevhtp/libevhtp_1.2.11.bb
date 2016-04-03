SUMMARY = "libevhtp - A user-friendly framework for high-performance http servers"
DESCRIPTION = "Libevhtp was created as a replacement API \
for Libevent's current HTTP API"
HOMEPAGE = "http://ellzey.github.io/libevhtp/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3461b04ca5f3d01aa98c6455ec7cdbbf"
DEPENDS = "libevent"
SRCREV = "c84f68d258d07c4015820ceb87fd17decd054bfc"

SRC_URI = "git://chromium.googlesource.com/external/github.com/ellzey/libevhtp.git;protocol=https"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE_append = " -DEVHTP_DISABLE_REGEX:BOOL=ON"
