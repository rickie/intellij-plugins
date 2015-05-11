/*
 * Copyright 2000-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.coldFusion;

import com.intellij.codeInsight.TargetElementUtil;
import com.intellij.codeInsight.hint.ImplementationViewComponent;
import com.intellij.psi.PsiElement;

/**
 * Created by IntelliJ IDEA.
 * User: Nadya.Zabrodina
 * Date: 4/24/12
 */
public class CfmlImplementationsViewTest extends CfmlCodeInsightFixtureTestCase {
  public void testQuickDefinitionViewForTagFunctions() {
    myFixture.configureByFile(Util.getInputDataFileName(getTestName(true)));
    PsiElement element =
      TargetElementUtil.findTargetElement(myFixture.getEditor(), TargetElementUtil.getInstance().getAllAccepted());
    assert element != null;
    final String newText = ImplementationViewComponent.getNewText(element.getNavigationElement());
    assertEquals(
      "<cffunction name=\"testIncludeFile\" hint=\"test including a file\" access=\"public\" returntype=\"void\" output=\"false\">\n" +
      "    <cfargument name=\"arg1\" type=\"numeric\">\n" +
      "    <cfargument name=\"arg2\" type=\"numeric\">\n" +
      "    <cfscript>\n" +
      "    </cfscript>\n" +
      "</cffunction>", newText);
  }


  @Override
  protected String getBasePath() {
    return "/implementationsView";
  }
}
