#_*_coding:utf8_*_
import sys, os
from pynput.keyboard import Controller,Key,Listener


def on_press(key):
    try:
        print("正在按压:",format(key.char))
    except AttributeError:
        print("正在按压:",format(key))


def on_release(key):
    print("已经释放:",format(key))

    if key==Key.esc:
       
        return False


def start_listen():
    with Listener(on_press,on_release) as listener:
        listener.join()

if __name__ == '__main__':

    
    kb=Controller()
    start_listen()
