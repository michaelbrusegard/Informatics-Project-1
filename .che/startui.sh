#!/bin/bash

vncserver -SecurityTypes None :0
/opt/novnc/utils/novnc_proxy --vnc localhost:5900 --listen 6080 &
