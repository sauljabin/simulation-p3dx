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

public class BoolWA
{
    boolean w[];

    public BoolWA(int i)
    {
        w = new boolean[i];
    }

    public void initArray(int i)
    {
        w = new boolean[i];
    }

    public boolean[] getArray()
    {
        return w;
    }

    public int getLength()
    {
        return w.length;
    }

    public boolean[] getNewArray(int i)
    {
        w = new boolean[i];
        return w;
    }
}
