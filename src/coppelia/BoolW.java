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

public class BoolW
{
    boolean w;

    public BoolW(boolean b)
    {
        w = b;
    }

    public void setValue(boolean b)
    {
        w = b;
    }

    public boolean getValue()
    {
        return w;
    }
}
