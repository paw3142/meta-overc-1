SUMMARY = "Go service to check version info and broadcast hashicorp bulletins"
HOMEPAGE = "https://github.com/hashicorp/go-checkpoint"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b278a92d2c1509760384428817710378"

PKG_NAME = "github.com/hashicorp/go-checkpoint"
SRC_URI = "git://${PKG_NAME}.git"
SRCREV = "88326f6851319068e7b34981032128c0b1a6524d"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${prefix}/local/go/src/${PKG_NAME}
    cp -a ${S}/* ${D}${prefix}/local/go/src/${PKG_NAME}/
}

SYSROOT_PREPROCESS_FUNCS += "go_checkpoint_sysroot_preprocess"

go_checkpoint_sysroot_preprocess () {
    install -d ${SYSROOT_DESTDIR}${prefix}/local/go/src/${PKG_NAME}
    cp -a ${D}${prefix}/local/go/src/${PKG_NAME} ${SYSROOT_DESTDIR}${prefix}/local/go/src/$(dirname ${PKG_NAME})
}

FILES_${PN} += "${prefix}/local/go/src/${PKG_NAME}/*"
