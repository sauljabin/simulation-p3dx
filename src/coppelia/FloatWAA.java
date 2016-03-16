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

public class FloatWAA
{
    FloatWA[] w;

    public FloatWAA(int i)
    {
        w = new FloatWA[i];
    }

    public void initArray(int i)
    {
        w = new FloatWA[i];
    }

    public FloatWA[] getArray()
    {
        return w;
    }

    public int getLength()
    {
        return w.length;
    }

    public FloatWA[] getNewArray(int i)
    {
        w = new FloatWA[i];
        for (int k = 0; k < i; k++)
            w[k] = new FloatWA(1);
        return w;
    }
}
