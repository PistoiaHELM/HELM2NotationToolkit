/**
 * *****************************************************************************
 * Copyright C 2015, The Pistoia Alliance
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *****************************************************************************
 */
package org.helm.notation2;

import java.lang.reflect.InvocationTargetException;

import org.helm.chemtoolkit.AbstractChemistryManipulator;
import org.helm.chemtoolkit.ManipulatorFactory;
import org.helm.chemtoolkit.ManipulatorFactory.ManipulatorType;

/**
 * Chemistry
 * 
 * @author hecht
 */
public class Chemistry {

  private static Chemistry instance;

  private static ManipulatorType type;

  private static AbstractChemistryManipulator manipulator;

  private Chemistry() {

  }

  public static Chemistry getInstance() {
    if (Chemistry.instance == null) {
      Chemistry.instance = new Chemistry();
      try {
        type = ManipulatorType.CDK;
        manipulator = ManipulatorFactory.buildManipulator(type);

      } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        e.printStackTrace();
      }
    }
    return Chemistry.instance;
  }

  public synchronized AbstractChemistryManipulator getManipulator() {
    return manipulator;
  }

  public ManipulatorType getManipulatorType() {
    return type;
  }
}
