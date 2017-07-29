/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eti.wiki.ui;

/**
 *
 * @author pawel
 */
public interface IParsingProgressListener {
    void progressChanged(int howMuch, int outOf);
    void currentlyProcessedPageChanged(String toPage);
}
