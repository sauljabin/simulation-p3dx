/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>, Jorge Parra <thejorgemylio@gmail.com>.
 * 
 * This file is part of SimulationP3DX.
 * 
 * SimulationP3DX is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

// PUT_VREP_REMOTEAPI_COPYRIGHT_NOTICE_HERE

package coppelia;

public class CharWA
{
    char[] w;

    public CharWA(int i)
    {
        w = new char[i];
    }

    public void initArray(int i)
    {
        w = new char[i];
    }

    public char[] getArray()
    {
        return w;
    }

    public int getLength()
    {
        return w.length;
    }

    public char[] getNewArray(int i)
    {
        w = new char[i];
        return w;
    }
}
