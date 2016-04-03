SUMMARY = "libWeave is the library with device side implementation of Weave protocol."
DESCRIPTION = "Weave is a communications platform for IoT device that \
enables device setup, phone-to-deivice-to-cloud communication, and user \
interaction from mobile devices and the web."
HOMEPAGE = "https://developers.google.com/weave"
LICENSE = "BSD | BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2d7a5dc96d830068356b88cc1d24ac31 \
    file://third_party/googletest/googletest/LICENSE;md5=cbbd27594afd089daa160d3a16dd515a \
    file://third_party/googletest/googlemock/LICENSE;md5=cbbd27594afd089daa160d3a16dd515a"

DEPENDS = "openssl expat libevhtp curl avahi"

SRCREV_libweave = "5fe0ac0af3f49e6ab96e18bdcc9e0a0749bbd99f"
SRCREV_googletest = "82b11b8cfcca464c2ac74b623d04e74452e74f32"

SRC_URI = "git://weave.googlesource.com/weave/libweave;protocol=https;name=libweave"
SRC_URI += "git://chromium.googlesource.com/external/github.com/google/googletest;protocol=https;destsuffix=git/third_party/googletest;name=googletest"

SRCREV_FORMAT = "libweave"

PV = "1.4.0+git${SRCPV}"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "USE_INTERNAL_LIBEVHTP=0"
EXTRA_OEMAKE += "LIBEVHTP_INCLUDES=-I${STAGING_INCDIR}/evhtp"
EXTRA_OEMAKE += "LIBEVHTP_HEADERS=${STAGING_INCDIR}/evhtp"

do_configure[noexec] = "1"

do_compile() {
    cd ${S}
    oe_runmake
}

do_install() {
    install -d ${D}${libdir}
    install -d ${D}${includedir}/weave
    install -d ${D}${includedir}/weave/provider
    install -d ${D}${bindir}

    install -m 0755 ${S}/out/Debug/libweave.so ${D}${libdir}
    install -m 0644 ${S}/out/Debug/libweave_common.a ${D}${libdir}

    install -m 0644 ${S}/include/weave/*.h ${D}${includedir}/weave
    install -m 0644 ${S}/include/weave/provider/*.h ${D}${includedir}/weave/provider

    install -m 0755 ${S}/out/Debug/libweave_testrunner ${D}${bindir}
    install -m 0755 ${S}/out/Debug/libweave_exports_testrunner ${D}${bindir}

    install -m 0755 ${S}/out/Debug/weave_daemon_ledflasher ${D}${bindir}
    install -m 0755 ${S}/out/Debug/weave_daemon_light ${D}${bindir}
    install -m 0755 ${S}/out/Debug/weave_daemon_lock ${D}${bindir}
    install -m 0755 ${S}/out/Debug/weave_daemon_oven ${D}${bindir}
    install -m 0755 ${S}/out/Debug/weave_daemon_sample ${D}${bindir}
    install -m 0755 ${S}/out/Debug/weave_daemon_speaker ${D}${bindir}
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-staticdev \
    ${PN}-dbg \
    ${PN}-tests \
    ${PN}-examples \
"

FILES_${PN} = " \
    ${libdir}/libweave.so \
"

FILES_${PN}-tests = " \
    ${bindir}/libweave_testrunner \
    ${bindir}/libweave_exports_testrunner \
"

FILES_${PN}-examples = " \
    ${bindir}/weave_daemon_ledflasher \
    ${bindir}/weave_daemon_light \
    ${bindir}/weave_daemon_lock \
    ${bindir}/weave_daemon_oven \
    ${bindir}/weave_daemon_sample \
    ${bindir}/weave_daemon_speaker \
"
