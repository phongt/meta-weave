DESCRIPTION = "A console-only image with libweave and examples"

IMAGE_FEATURES += "ssh-server-openssh"

CORE_IMAGE_EXTRA_INSTALL_append = " \
    libweave \
    libweave-tests \
    libweave-examples \
    avahi-daemon \
"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    packagegroup-core-full-cmdline \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

inherit core-image
