OS=`uname -s`
REV=`uname -r`
MACH=`uname -m`

GetVersionFromFile()
{
VERSION=`cat $1 | tr "\n" ' ' | sed s/.*VERSION.*=\ // `
}

if [ "${OS}" = "SunOS" ] ; then
    OS=Solaris
    ARCH=`uname -p`
    OSSTR="${OS} ${REV}(${ARCH} `uname -v`)"
elif [ "${OS}" = "AIX" ] ; then
    OSSTR="${OS} `oslevel` (`oslevel -r`)"
elif [ "${OS}" = "Linux" ] ; then
    KERNEL=`uname -r`
if [ -f /etc/redhat-release ] ; then
    DIST='RedHat'
    PSUEDONAME=`cat /etc/redhat-release | sed s/.*\(// | sed s/\)//`
    REV=`cat /etc/redhat-release | sed s/.*release\ // | sed s/\ .*//`
elif [ -f /etc/SuSE-release ] ; then
    DIST=`SuSE`
    REV=`cat /etc/SuSE-release | tr "\n" ' ' | sed s/.*=\ //`
elif [ -f /etc/mandrake-release ] ; then
    DIST='Mandrake'
    PSUEDONAME=`cat /etc/mandrake-release | sed s/.*\(// | sed s/\)//`
    REV=`cat /etc/mandrake-release | sed s/.*release\ // | sed s/\ .*//`
elif [ -f /etc/debian_version ] ; then
    DIST="Debian"
    REV=""
fi

if [ -f /etc/UnitedLinux-release ] ; then
    DIST="${DIST}[`cat /etc/UnitedLinux-release | tr "\n" ' ' | sed s/VERSION.*//`]"
fi

    OSSTR="${OS} ${DIST} ${REV}(${PSUEDONAME} ${KERNEL} ${MACH})"

fi

if [ "${OS}" == 'Darwin' ]; then
    if ! brew --version > /dev/null; then
        echo "Installing brew"
        /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
    fi
    echo "Installing git"
    brew install git
elif [ "${DIST}" == 'Debian' ]; then
    apt update
    apt install git-all
elif [ "${DIST}" == 'RedHat' ]; then
    yum update
    yum install git
else
    echo "Sorry, we could not install git on your system ${OS}, ${DistroBasedOn}"
fi
