require recipes-samples/images/rpb-weston-image.bb

SUMMARY = "Basic Wayland image with WPE on Westeros"

CORE_IMAGE_BASE_INSTALL += " \
    westeros \
    wpe \
    wpe-launcher \
    optee-os \
    optee-client \
    optee-aes-decryptor \
    portmap \
    ocdmi \
    opencdm \
"

CORE_IMAGE_BASE_INSTALL_remove = " \
    weston \
    weston-examples \
    weston-init \
"

EXTRA_USERS_PARAMS = " "
