/*
 * Copyright (c) 2009-2013 Panxiaobo
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pxb.android.axml;

import java.io.File;
import java.lang.reflect.Field;

public class FixManifestAdapter extends AxmlVisitor {

    private static String Android_NS = "http://schemas.android.com/apk/res/android";

    public static void generateCase() throws IllegalArgumentException, IllegalAccessException, SecurityException,
            ClassNotFoundException {
        for (Field f : R.attr.class.getDeclaredFields()) {
            Integer integer = (Integer) f.get(null);
            String n = f.getName();
            System.out
                    .println("case 0x" + Integer.toHexString(integer) + ": ns=Android_NS; name=\"" + n + "\"; break;");
        }
    }

    static boolean isEmpty(String e) {
        return e != null && e.length() == 0;
    }

    static boolean isNullOrEmpty(String e) {
        return e == null || e.length() == 0;
    }

    public static void main(String... args) throws Exception {
        if (args.length < 2) {
            System.err.println("FixManifestAdapter in out");
            return;
        }

        byte[] xml = Util.readFile(new File(args[0]));

        AxmlReader rd = new AxmlReader(xml);
        AxmlWriter wr = new AxmlWriter();
        rd.accept(new FixManifestAdapter(wr));

        byte[] modified = wr.toByteArray();
        Util.writeFile(modified, new File(args[1]));
    }

    public FixManifestAdapter() {
    }

    public FixManifestAdapter(NodeVisitor av) {
        super(av);
    }

    @Override
    public void attr(String ns, String name, int resourceId, int type, Object obj) {
        if (isEmpty(ns)) {
            ns = null;
        }
        switch (resourceId) {
        case 0x101006a:
            ns = Android_NS;
            name = "absListViewStyle";
            break;
        case 0x1010380:
            ns = Android_NS;
            name = "accessibilityEventTypes";
            break;
        case 0x1010382:
            ns = Android_NS;
            name = "accessibilityFeedbackType";
            break;
        case 0x1010384:
            ns = Android_NS;
            name = "accessibilityFlags";
            break;
        case 0x101029f:
            ns = Android_NS;
            name = "accountPreferences";
            break;
        case 0x101028f:
            ns = Android_NS;
            name = "accountType";
            break;
        case 0x101002d:
            ns = Android_NS;
            name = "action";
            break;
        case 0x101039b:
            ns = Android_NS;
            name = "actionBarDivider";
            break;
        case 0x101039c:
            ns = Android_NS;
            name = "actionBarItemBackground";
            break;
        case 0x10102eb:
            ns = Android_NS;
            name = "actionBarSize";
            break;
        case 0x1010388:
            ns = Android_NS;
            name = "actionBarSplitStyle";
            break;
        case 0x10102ce:
            ns = Android_NS;
            name = "actionBarStyle";
            break;
        case 0x10102f4:
            ns = Android_NS;
            name = "actionBarTabBarStyle";
            break;
        case 0x10102f3:
            ns = Android_NS;
            name = "actionBarTabStyle";
            break;
        case 0x10102f5:
            ns = Android_NS;
            name = "actionBarTabTextStyle";
            break;
        case 0x1010397:
            ns = Android_NS;
            name = "actionBarWidgetTheme";
            break;
        case 0x10102d8:
            ns = Android_NS;
            name = "actionButtonStyle";
            break;
        case 0x10102d7:
            ns = Android_NS;
            name = "actionDropDownStyle";
            break;
        case 0x10102fb:
            ns = Android_NS;
            name = "actionLayout";
            break;
        case 0x1010360:
            ns = Android_NS;
            name = "actionMenuTextAppearance";
            break;
        case 0x1010361:
            ns = Android_NS;
            name = "actionMenuTextColor";
            break;
        case 0x10102db:
            ns = Android_NS;
            name = "actionModeBackground";
            break;
        case 0x10102f7:
            ns = Android_NS;
            name = "actionModeCloseButtonStyle";
            break;
        case 0x10102dc:
            ns = Android_NS;
            name = "actionModeCloseDrawable";
            break;
        case 0x1010312:
            ns = Android_NS;
            name = "actionModeCopyDrawable";
            break;
        case 0x1010311:
            ns = Android_NS;
            name = "actionModeCutDrawable";
            break;
        case 0x1010313:
            ns = Android_NS;
            name = "actionModePasteDrawable";
            break;
        case 0x101037e:
            ns = Android_NS;
            name = "actionModeSelectAllDrawable";
            break;
        case 0x101039d:
            ns = Android_NS;
            name = "actionModeSplitBackground";
            break;
        case 0x1010394:
            ns = Android_NS;
            name = "actionModeStyle";
            break;
        case 0x10102f6:
            ns = Android_NS;
            name = "actionOverflowButtonStyle";
            break;
        case 0x1010389:
            ns = Android_NS;
            name = "actionProviderClass";
            break;
        case 0x10102fc:
            ns = Android_NS;
            name = "actionViewClass";
            break;
        case 0x10102fd:
            ns = Android_NS;
            name = "activatedBackgroundIndicator";
            break;
        case 0x10100ba:
            ns = Android_NS;
            name = "activityCloseEnterAnimation";
            break;
        case 0x10100bb:
            ns = Android_NS;
            name = "activityCloseExitAnimation";
            break;
        case 0x10100b8:
            ns = Android_NS;
            name = "activityOpenEnterAnimation";
            break;
        case 0x10100b9:
            ns = Android_NS;
            name = "activityOpenExitAnimation";
            break;
        case 0x10100f0:
            ns = Android_NS;
            name = "addStatesFromChildren";
            break;
        case 0x101011e:
            ns = Android_NS;
            name = "adjustViewBounds";
            break;
        case 0x1010355:
            ns = Android_NS;
            name = "alertDialogIcon";
            break;
        case 0x101005d:
            ns = Android_NS;
            name = "alertDialogStyle";
            break;
        case 0x1010309:
            ns = Android_NS;
            name = "alertDialogTheme";
            break;
        case 0x101037a:
            ns = Android_NS;
            name = "alignmentMode";
            break;
        case 0x10102cc:
            ns = Android_NS;
            name = "allContactsName";
            break;
        case 0x1010280:
            ns = Android_NS;
            name = "allowBackup";
            break;
        case 0x1010005:
            ns = Android_NS;
            name = "allowClearUserData";
            break;
        case 0x1010332:
            ns = Android_NS;
            name = "allowParallelSyncs";
            break;
        case 0x1010259:
            ns = Android_NS;
            name = "allowSingleTap";
            break;
        case 0x1010204:
            ns = Android_NS;
            name = "allowTaskReparenting";
            break;
        case 0x101031f:
            ns = Android_NS;
            name = "alpha";
            break;
        case 0x10101e3:
            ns = Android_NS;
            name = "alphabeticShortcut";
            break;
        case 0x10100ef:
            ns = Android_NS;
            name = "alwaysDrawnWithCache";
            break;
        case 0x1010203:
            ns = Android_NS;
            name = "alwaysRetainTaskState";
            break;
        case 0x10101a0:
            ns = Android_NS;
            name = "angle";
            break;
        case 0x10102d5:
            ns = Android_NS;
            name = "animateFirstView";
            break;
        case 0x10102f2:
            ns = Android_NS;
            name = "animateLayoutChanges";
            break;
        case 0x101025c:
            ns = Android_NS;
            name = "animateOnClick";
            break;
        case 0x10101cd:
            ns = Android_NS;
            name = "animation";
            break;
        case 0x10100ed:
            ns = Android_NS;
            name = "animationCache";
            break;
        case 0x1010112:
            ns = Android_NS;
            name = "animationDuration";
            break;
        case 0x10101ce:
            ns = Android_NS;
            name = "animationOrder";
            break;
        case 0x101031a:
            ns = Android_NS;
            name = "animationResolution";
            break;
        case 0x101011a:
            ns = Android_NS;
            name = "antialias";
            break;
        case 0x101026c:
            ns = Android_NS;
            name = "anyDensity";
            break;
        case 0x1010211:
            ns = Android_NS;
            name = "apiKey";
            break;
        case 0x10102b4:
            ns = Android_NS;
            name = "author";
            break;
        case 0x1010018:
            ns = Android_NS;
            name = "authorities";
            break;
        case 0x101030f:
            ns = Android_NS;
            name = "autoAdvanceViewId";
            break;
        case 0x101006b:
            ns = Android_NS;
            name = "autoCompleteTextViewStyle";
            break;
        case 0x10100b0:
            ns = Android_NS;
            name = "autoLink";
            break;
        case 0x10102b5:
            ns = Android_NS;
            name = "autoStart";
            break;
        case 0x101016a:
            ns = Android_NS;
            name = "autoText";
            break;
        case 0x101028c:
            ns = Android_NS;
            name = "autoUrlDetect";
            break;
        case 0x10100d4:
            ns = Android_NS;
            name = "background";
            break;
        case 0x1010032:
            ns = Android_NS;
            name = "backgroundDimAmount";
            break;
        case 0x101021f:
            ns = Android_NS;
            name = "backgroundDimEnabled";
            break;
        case 0x101038b:
            ns = Android_NS;
            name = "backgroundSplit";
            break;
        case 0x101038a:
            ns = Android_NS;
            name = "backgroundStacked";
            break;
        case 0x101027f:
            ns = Android_NS;
            name = "backupAgent";
            break;
        case 0x101031c:
            ns = Android_NS;
            name = "baseline";
            break;
        case 0x1010122:
            ns = Android_NS;
            name = "baselineAlignBottom";
            break;
        case 0x1010126:
            ns = Android_NS;
            name = "baselineAligned";
            break;
        case 0x1010127:
            ns = Android_NS;
            name = "baselineAlignedChildIndex";
            break;
        case 0x101032b:
            ns = Android_NS;
            name = "borderlessButtonStyle";
            break;
        case 0x10101b0:
            ns = Android_NS;
            name = "bottom";
            break;
        case 0x10100cd:
            ns = Android_NS;
            name = "bottomBright";
            break;
        case 0x10100c9:
            ns = Android_NS;
            name = "bottomDark";
            break;
        case 0x10101ab:
            ns = Android_NS;
            name = "bottomLeftRadius";
            break;
        case 0x10100ce:
            ns = Android_NS;
            name = "bottomMedium";
            break;
        case 0x1010257:
            ns = Android_NS;
            name = "bottomOffset";
            break;
        case 0x10101ac:
            ns = Android_NS;
            name = "bottomRightRadius";
            break;
        case 0x1010304:
            ns = Android_NS;
            name = "breadCrumbShortTitle";
            break;
        case 0x1010303:
            ns = Android_NS;
            name = "breadCrumbTitle";
            break;
        case 0x101014e:
            ns = Android_NS;
            name = "bufferType";
            break;
        case 0x1010107:
            ns = Android_NS;
            name = "button";
            break;
        case 0x101032f:
            ns = Android_NS;
            name = "buttonBarButtonStyle";
            break;
        case 0x101032e:
            ns = Android_NS;
            name = "buttonBarStyle";
            break;
        case 0x1010048:
            ns = Android_NS;
            name = "buttonStyle";
            break;
        case 0x101004a:
            ns = Android_NS;
            name = "buttonStyleInset";
            break;
        case 0x1010049:
            ns = Android_NS;
            name = "buttonStyleSmall";
            break;
        case 0x101004b:
            ns = Android_NS;
            name = "buttonStyleToggle";
            break;
        case 0x1010101:
            ns = Android_NS;
            name = "cacheColorHint";
            break;
        case 0x101034c:
            ns = Android_NS;
            name = "calendarViewShown";
            break;
        case 0x101035d:
            ns = Android_NS;
            name = "calendarViewStyle";
            break;
        case 0x1010385:
            ns = Android_NS;
            name = "canRetrieveWindowContent";
            break;
        case 0x1010230:
            ns = Android_NS;
            name = "candidatesTextStyleSpans";
            break;
        case 0x1010169:
            ns = Android_NS;
            name = "capitalize";
            break;
        case 0x10100cc:
            ns = Android_NS;
            name = "centerBright";
            break;
        case 0x101020b:
            ns = Android_NS;
            name = "centerColor";
            break;
        case 0x10100c8:
            ns = Android_NS;
            name = "centerDark";
            break;
        case 0x10100cf:
            ns = Android_NS;
            name = "centerMedium";
            break;
        case 0x10101a2:
            ns = Android_NS;
            name = "centerX";
            break;
        case 0x10101a3:
            ns = Android_NS;
            name = "centerY";
            break;
        case 0x101008f:
            ns = Android_NS;
            name = "checkBoxPreferenceStyle";
            break;
        case 0x1010108:
            ns = Android_NS;
            name = "checkMark";
            break;
        case 0x10101e5:
            ns = Android_NS;
            name = "checkable";
            break;
        case 0x10101e0:
            ns = Android_NS;
            name = "checkableBehavior";
            break;
        case 0x101006c:
            ns = Android_NS;
            name = "checkboxStyle";
            break;
        case 0x1010106:
            ns = Android_NS;
            name = "checked";
            break;
        case 0x1010148:
            ns = Android_NS;
            name = "checkedButton";
            break;
        case 0x10103c8:
            ns = Android_NS;
            name = "checkedTextViewStyle";
            break;
        case 0x1010111:
            ns = Android_NS;
            name = "childDivider";
            break;
        case 0x101010c:
            ns = Android_NS;
            name = "childIndicator";
            break;
        case 0x101010f:
            ns = Android_NS;
            name = "childIndicatorLeft";
            break;
        case 0x1010110:
            ns = Android_NS;
            name = "childIndicatorRight";
            break;
        case 0x101012b:
            ns = Android_NS;
            name = "choiceMode";
            break;
        case 0x1010015:
            ns = Android_NS;
            name = "clearTaskOnLaunch";
            break;
        case 0x10100e5:
            ns = Android_NS;
            name = "clickable";
            break;
        case 0x10100ea:
            ns = Android_NS;
            name = "clipChildren";
            break;
        case 0x101020a:
            ns = Android_NS;
            name = "clipOrientation";
            break;
        case 0x10100eb:
            ns = Android_NS;
            name = "clipToPadding";
            break;
        case 0x1010242:
            ns = Android_NS;
            name = "codes";
            break;
        case 0x101014b:
            ns = Android_NS;
            name = "collapseColumns";
            break;
        case 0x10101a5:
            ns = Android_NS;
            name = "color";
            break;
        case 0x1010390:
            ns = Android_NS;
            name = "colorActivatedHighlight";
            break;
        case 0x1010031:
            ns = Android_NS;
            name = "colorBackground";
            break;
        case 0x10102ab:
            ns = Android_NS;
            name = "colorBackgroundCacheHint";
            break;
        case 0x101038f:
            ns = Android_NS;
            name = "colorFocusedHighlight";
            break;
        case 0x1010030:
            ns = Android_NS;
            name = "colorForeground";
            break;
        case 0x1010206:
            ns = Android_NS;
            name = "colorForegroundInverse";
            break;
        case 0x101038e:
            ns = Android_NS;
            name = "colorLongPressedHighlight";
            break;
        case 0x1010391:
            ns = Android_NS;
            name = "colorMultiSelectHighlight";
            break;
        case 0x101038d:
            ns = Android_NS;
            name = "colorPressedHighlight";
            break;
        case 0x1010377:
            ns = Android_NS;
            name = "columnCount";
            break;
        case 0x10101cf:
            ns = Android_NS;
            name = "columnDelay";
            break;
        case 0x1010378:
            ns = Android_NS;
            name = "columnOrderPreserved";
            break;
        case 0x1010117:
            ns = Android_NS;
            name = "columnWidth";
            break;
        case 0x1010365:
            ns = Android_NS;
            name = "compatibleWidthLimitDp";
            break;
        case 0x1010172:
            ns = Android_NS;
            name = "completionHint";
            break;
        case 0x1010173:
            ns = Android_NS;
            name = "completionHintView";
            break;
        case 0x1010174:
            ns = Android_NS;
            name = "completionThreshold";
            break;
        case 0x101001f:
            ns = Android_NS;
            name = "configChanges";
            break;
        case 0x101025d:
            ns = Android_NS;
            name = "configure";
            break;
        case 0x1010196:
            ns = Android_NS;
            name = "constantSize";
            break;
        case 0x101025b:
            ns = Android_NS;
            name = "content";
            break;
        case 0x1010290:
            ns = Android_NS;
            name = "contentAuthority";
            break;
        case 0x1010273:
            ns = Android_NS;
            name = "contentDescription";
            break;
        case 0x1010123:
            ns = Android_NS;
            name = "cropToPadding";
            break;
        case 0x1010152:
            ns = Android_NS;
            name = "cursorVisible";
            break;
        case 0x10102d2:
            ns = Android_NS;
            name = "customNavigationLayout";
            break;
        case 0x101033b:
            ns = Android_NS;
            name = "customTokens";
            break;
        case 0x10101d4:
            ns = Android_NS;
            name = "cycles";
            break;
        case 0x10101a7:
            ns = Android_NS;
            name = "dashGap";
            break;
        case 0x10101a6:
            ns = Android_NS;
            name = "dashWidth";
            break;
        case 0x101002e:
            ns = Android_NS;
            name = "data";
            break;
        case 0x101035c:
            ns = Android_NS;
            name = "datePickerStyle";
            break;
        case 0x1010349:
            ns = Android_NS;
            name = "dateTextAppearance";
            break;
        case 0x101000f:
            ns = Android_NS;
            name = "debuggable";
            break;
        case 0x10101ed:
            ns = Android_NS;
            name = "defaultValue";
            break;
        case 0x10101cc:
            ns = Android_NS;
            name = "delay";
            break;
        case 0x10101ec:
            ns = Android_NS;
            name = "dependency";
            break;
        case 0x10100f1:
            ns = Android_NS;
            name = "descendantFocusability";
            break;
        case 0x1010020:
            ns = Android_NS;
            name = "description";
            break;
        case 0x10102a6:
            ns = Android_NS;
            name = "detachWallpaper";
            break;
        case 0x10102a3:
            ns = Android_NS;
            name = "detailColumn";
            break;
        case 0x10102a4:
            ns = Android_NS;
            name = "detailSocialSummary";
            break;
        case 0x101034e:
            ns = Android_NS;
            name = "detailsElementBackground";
            break;
        case 0x1010102:
            ns = Android_NS;
            name = "dial";
            break;
        case 0x10101f4:
            ns = Android_NS;
            name = "dialogIcon";
            break;
        case 0x10101f7:
            ns = Android_NS;
            name = "dialogLayout";
            break;
        case 0x10101f3:
            ns = Android_NS;
            name = "dialogMessage";
            break;
        case 0x1010091:
            ns = Android_NS;
            name = "dialogPreferenceStyle";
            break;
        case 0x1010308:
            ns = Android_NS;
            name = "dialogTheme";
            break;
        case 0x10101f2:
            ns = Android_NS;
            name = "dialogTitle";
            break;
        case 0x1010166:
            ns = Android_NS;
            name = "digits";
            break;
        case 0x10101d1:
            ns = Android_NS;
            name = "direction";
            break;
        case 0x10103a1:
            ns = Android_NS;
            name = "directionDescriptions";
            break;
        case 0x10101d2:
            ns = Android_NS;
            name = "directionPriority";
            break;
        case 0x10101f1:
            ns = Android_NS;
            name = "disableDependentsState";
            break;
        case 0x1010033:
            ns = Android_NS;
            name = "disabledAlpha";
            break;
        case 0x10102d0:
            ns = Android_NS;
            name = "displayOptions";
            break;
        case 0x101011c:
            ns = Android_NS;
            name = "dither";
            break;
        case 0x1010129:
            ns = Android_NS;
            name = "divider";
            break;
        case 0x101012a:
            ns = Android_NS;
            name = "dividerHeight";
            break;
        case 0x101032c:
            ns = Android_NS;
            name = "dividerHorizontal";
            break;
        case 0x101032a:
            ns = Android_NS;
            name = "dividerPadding";
            break;
        case 0x101030a:
            ns = Android_NS;
            name = "dividerVertical";
            break;
        case 0x10100fc:
            ns = Android_NS;
            name = "drawSelectorOnTop";
            break;
        case 0x1010199:
            ns = Android_NS;
            name = "drawable";
            break;
        case 0x101016e:
            ns = Android_NS;
            name = "drawableBottom";
            break;
        case 0x1010393:
            ns = Android_NS;
            name = "drawableEnd";
            break;
        case 0x101016f:
            ns = Android_NS;
            name = "drawableLeft";
            break;
        case 0x1010171:
            ns = Android_NS;
            name = "drawablePadding";
            break;
        case 0x1010170:
            ns = Android_NS;
            name = "drawableRight";
            break;
        case 0x1010392:
            ns = Android_NS;
            name = "drawableStart";
            break;
        case 0x101016d:
            ns = Android_NS;
            name = "drawableTop";
            break;
        case 0x10100e8:
            ns = Android_NS;
            name = "drawingCacheQuality";
            break;
        case 0x1010263:
            ns = Android_NS;
            name = "dropDownAnchor";
            break;
        case 0x1010283:
            ns = Android_NS;
            name = "dropDownHeight";
            break;
        case 0x1010088:
            ns = Android_NS;
            name = "dropDownHintAppearance";
            break;
        case 0x10102ac:
            ns = Android_NS;
            name = "dropDownHorizontalOffset";
            break;
        case 0x1010086:
            ns = Android_NS;
            name = "dropDownItemStyle";
            break;
        case 0x101006d:
            ns = Android_NS;
            name = "dropDownListViewStyle";
            break;
        case 0x1010175:
            ns = Android_NS;
            name = "dropDownSelector";
            break;
        case 0x10102d6:
            ns = Android_NS;
            name = "dropDownSpinnerStyle";
            break;
        case 0x10102ad:
            ns = Android_NS;
            name = "dropDownVerticalOffset";
            break;
        case 0x1010262:
            ns = Android_NS;
            name = "dropDownWidth";
            break;
        case 0x10100e9:
            ns = Android_NS;
            name = "duplicateParentState";
            break;
        case 0x1010198:
            ns = Android_NS;
            name = "duration";
            break;
        case 0x1010352:
            ns = Android_NS;
            name = "editTextBackground";
            break;
        case 0x1010351:
            ns = Android_NS;
            name = "editTextColor";
            break;
        case 0x1010092:
            ns = Android_NS;
            name = "editTextPreferenceStyle";
            break;
        case 0x101006e:
            ns = Android_NS;
            name = "editTextStyle";
            break;
        case 0x101016b:
            ns = Android_NS;
            name = "editable";
            break;
        case 0x1010224:
            ns = Android_NS;
            name = "editorExtras";
            break;
        case 0x10100ab:
            ns = Android_NS;
            name = "ellipsize";
            break;
        case 0x1010158:
            ns = Android_NS;
            name = "ems";
            break;
        case 0x101000e:
            ns = Android_NS;
            name = "enabled";
            break;
        case 0x101019e:
            ns = Android_NS;
            name = "endColor";
            break;
        case 0x101017d:
            ns = Android_NS;
            name = "endYear";
            break;
        case 0x101030c:
            ns = Android_NS;
            name = "enterFadeDuration";
            break;
        case 0x10100b2:
            ns = Android_NS;
            name = "entries";
            break;
        case 0x10101f8:
            ns = Android_NS;
            name = "entryValues";
            break;
        case 0x101027d:
            ns = Android_NS;
            name = "eventsInterceptionEnabled";
            break;
        case 0x1010017:
            ns = Android_NS;
            name = "excludeFromRecents";
            break;
        case 0x101030d:
            ns = Android_NS;
            name = "exitFadeDuration";
            break;
        case 0x1010052:
            ns = Android_NS;
            name = "expandableListPreferredChildIndicatorLeft";
            break;
        case 0x1010053:
            ns = Android_NS;
            name = "expandableListPreferredChildIndicatorRight";
            break;
        case 0x101004f:
            ns = Android_NS;
            name = "expandableListPreferredChildPaddingLeft";
            break;
        case 0x1010050:
            ns = Android_NS;
            name = "expandableListPreferredItemIndicatorLeft";
            break;
        case 0x1010051:
            ns = Android_NS;
            name = "expandableListPreferredItemIndicatorRight";
            break;
        case 0x101004e:
            ns = Android_NS;
            name = "expandableListPreferredItemPaddingLeft";
            break;
        case 0x101006f:
            ns = Android_NS;
            name = "expandableListViewStyle";
            break;
        case 0x10102b6:
            ns = Android_NS;
            name = "expandableListViewWhiteStyle";
            break;
        case 0x1010010:
            ns = Android_NS;
            name = "exported";
            break;
        case 0x101026b:
            ns = Android_NS;
            name = "extraTension";
            break;
        case 0x10101d3:
            ns = Android_NS;
            name = "factor";
            break;
        case 0x1010278:
            ns = Android_NS;
            name = "fadeDuration";
            break;
        case 0x101027e:
            ns = Android_NS;
            name = "fadeEnabled";
            break;
        case 0x1010277:
            ns = Android_NS;
            name = "fadeOffset";
            break;
        case 0x10102aa:
            ns = Android_NS;
            name = "fadeScrollbars";
            break;
        case 0x10100df:
            ns = Android_NS;
            name = "fadingEdge";
            break;
        case 0x10100e0:
            ns = Android_NS;
            name = "fadingEdgeLength";
            break;
        case 0x1010335:
            ns = Android_NS;
            name = "fastScrollAlwaysVisible";
            break;
        case 0x1010226:
            ns = Android_NS;
            name = "fastScrollEnabled";
            break;
        case 0x101033a:
            ns = Android_NS;
            name = "fastScrollOverlayPosition";
            break;
        case 0x1010337:
            ns = Android_NS;
            name = "fastScrollPreviewBackgroundLeft";
            break;
        case 0x1010338:
            ns = Android_NS;
            name = "fastScrollPreviewBackgroundRight";
            break;
        case 0x1010359:
            ns = Android_NS;
            name = "fastScrollTextColor";
            break;
        case 0x1010336:
            ns = Android_NS;
            name = "fastScrollThumbDrawable";
            break;
        case 0x1010339:
            ns = Android_NS;
            name = "fastScrollTrackDrawable";
            break;
        case 0x10101bd:
            ns = Android_NS;
            name = "fillAfter";
            break;
        case 0x10101bc:
            ns = Android_NS;
            name = "fillBefore";
            break;
        case 0x101024f:
            ns = Android_NS;
            name = "fillEnabled";
            break;
        case 0x101017a:
            ns = Android_NS;
            name = "fillViewport";
            break;
        case 0x101011b:
            ns = Android_NS;
            name = "filter";
            break;
        case 0x10102c4:
            ns = Android_NS;
            name = "filterTouchesWhenObscured";
            break;
        case 0x10102a7:
            ns = Android_NS;
            name = "finishOnCloseSystemDialogs";
            break;
        case 0x1010014:
            ns = Android_NS;
            name = "finishOnTaskLaunch";
            break;
        case 0x101033d:
            ns = Android_NS;
            name = "firstDayOfWeek";
            break;
        case 0x10100dd:
            ns = Android_NS;
            name = "fitsSystemWindows";
            break;
        case 0x1010179:
            ns = Android_NS;
            name = "flipInterval";
            break;
        case 0x10100da:
            ns = Android_NS;
            name = "focusable";
            break;
        case 0x10100db:
            ns = Android_NS;
            name = "focusableInTouchMode";
            break;
        case 0x1010343:
            ns = Android_NS;
            name = "focusedMonthDateColor";
            break;
        case 0x10103ac:
            ns = Android_NS;
            name = "fontFamily";
            break;
        case 0x101022f:
            ns = Android_NS;
            name = "footerDividersEnabled";
            break;
        case 0x1010109:
            ns = Android_NS;
            name = "foreground";
            break;
        case 0x1010200:
            ns = Android_NS;
            name = "foregroundGravity";
            break;
        case 0x1010105:
            ns = Android_NS;
            name = "format";
            break;
        case 0x10103ca:
            ns = Android_NS;
            name = "format12Hour";
            break;
        case 0x10103cb:
            ns = Android_NS;
            name = "format24Hour";
            break;
        case 0x10102e3:
            ns = Android_NS;
            name = "fragment";
            break;
        case 0x10102e7:
            ns = Android_NS;
            name = "fragmentCloseEnterAnimation";
            break;
        case 0x10102e8:
            ns = Android_NS;
            name = "fragmentCloseExitAnimation";
            break;
        case 0x10102e9:
            ns = Android_NS;
            name = "fragmentFadeEnterAnimation";
            break;
        case 0x10102ea:
            ns = Android_NS;
            name = "fragmentFadeExitAnimation";
            break;
        case 0x10102e5:
            ns = Android_NS;
            name = "fragmentOpenEnterAnimation";
            break;
        case 0x10102e6:
            ns = Android_NS;
            name = "fragmentOpenExitAnimation";
            break;
        case 0x101016c:
            ns = Android_NS;
            name = "freezesText";
            break;
        case 0x10101ca:
            ns = Android_NS;
            name = "fromAlpha";
            break;
        case 0x10101b3:
            ns = Android_NS;
            name = "fromDegrees";
            break;
        case 0x10101c6:
            ns = Android_NS;
            name = "fromXDelta";
            break;
        case 0x10101c2:
            ns = Android_NS;
            name = "fromXScale";
            break;
        case 0x10101c8:
            ns = Android_NS;
            name = "fromYDelta";
            break;
        case 0x10101c4:
            ns = Android_NS;
            name = "fromYScale";
            break;
        case 0x10100ca:
            ns = Android_NS;
            name = "fullBright";
            break;
        case 0x10100c6:
            ns = Android_NS;
            name = "fullDark";
            break;
        case 0x1010023:
            ns = Android_NS;
            name = "functionalTest";
            break;
        case 0x101004c:
            ns = Android_NS;
            name = "galleryItemBackground";
            break;
        case 0x1010070:
            ns = Android_NS;
            name = "galleryStyle";
            break;
        case 0x1010275:
            ns = Android_NS;
            name = "gestureColor";
            break;
        case 0x101027c:
            ns = Android_NS;
            name = "gestureStrokeAngleThreshold";
            break;
        case 0x101027a:
            ns = Android_NS;
            name = "gestureStrokeLengthThreshold";
            break;
        case 0x101027b:
            ns = Android_NS;
            name = "gestureStrokeSquarenessThreshold";
            break;
        case 0x1010279:
            ns = Android_NS;
            name = "gestureStrokeType";
            break;
        case 0x1010274:
            ns = Android_NS;
            name = "gestureStrokeWidth";
            break;
        case 0x1010281:
            ns = Android_NS;
            name = "glEsVersion";
            break;
        case 0x10101a4:
            ns = Android_NS;
            name = "gradientRadius";
            break;
        case 0x101001b:
            ns = Android_NS;
            name = "grantUriPermissions";
            break;
        case 0x10100af:
            ns = Android_NS;
            name = "gravity";
            break;
        case 0x1010071:
            ns = Android_NS;
            name = "gridViewStyle";
            break;
        case 0x101010b:
            ns = Android_NS;
            name = "groupIndicator";
            break;
        case 0x1010103:
            ns = Android_NS;
            name = "hand_hour";
            break;
        case 0x1010104:
            ns = Android_NS;
            name = "hand_minute";
            break;
        case 0x101025a:
            ns = Android_NS;
            name = "handle";
            break;
        case 0x1010022:
            ns = Android_NS;
            name = "handleProfiling";
            break;
        case 0x101025e:
            ns = Android_NS;
            name = "hapticFeedbackEnabled";
            break;
        case 0x10102d3:
            ns = Android_NS;
            name = "hardwareAccelerated";
            break;
        case 0x101000c:
            ns = Android_NS;
            name = "hasCode";
            break;
        case 0x101012f:
            ns = Android_NS;
            name = "headerBackground";
            break;
        case 0x101022e:
            ns = Android_NS;
            name = "headerDividersEnabled";
            break;
        case 0x1010155:
            ns = Android_NS;
            name = "height";
            break;
        case 0x1010150:
            ns = Android_NS;
            name = "hint";
            break;
        case 0x101030b:
            ns = Android_NS;
            name = "homeAsUpIndicator";
            break;
        case 0x101031d:
            ns = Android_NS;
            name = "homeLayout";
            break;
        case 0x101012d:
            ns = Android_NS;
            name = "horizontalDivider";
            break;
        case 0x101023f:
            ns = Android_NS;
            name = "horizontalGap";
            break;
        case 0x1010353:
            ns = Android_NS;
            name = "horizontalScrollViewStyle";
            break;
        case 0x1010114:
            ns = Android_NS;
            name = "horizontalSpacing";
            break;
        case 0x1010028:
            ns = Android_NS;
            name = "host";
            break;
        case 0x1010002:
            ns = Android_NS;
            name = "icon";
            break;
        case 0x1010249:
            ns = Android_NS;
            name = "iconPreview";
            break;
        case 0x10102fa:
            ns = Android_NS;
            name = "iconifiedByDefault";
            break;
        case 0x10100d0:
            ns = Android_NS;
            name = "id";
            break;
        case 0x10101ff:
            ns = Android_NS;
            name = "ignoreGravity";
            break;
        case 0x1010072:
            ns = Android_NS;
            name = "imageButtonStyle";
            break;
        case 0x1010073:
            ns = Android_NS;
            name = "imageWellStyle";
            break;
        case 0x1010266:
            ns = Android_NS;
            name = "imeActionId";
            break;
        case 0x1010265:
            ns = Android_NS;
            name = "imeActionLabel";
            break;
        case 0x1010268:
            ns = Android_NS;
            name = "imeExtractEnterAnimation";
            break;
        case 0x1010269:
            ns = Android_NS;
            name = "imeExtractExitAnimation";
            break;
        case 0x101022c:
            ns = Android_NS;
            name = "imeFullscreenBackground";
            break;
        case 0x1010264:
            ns = Android_NS;
            name = "imeOptions";
            break;
        case 0x10102ee:
            ns = Android_NS;
            name = "imeSubtypeExtraValue";
            break;
        case 0x10102ec:
            ns = Android_NS;
            name = "imeSubtypeLocale";
            break;
        case 0x10102ed:
            ns = Android_NS;
            name = "imeSubtypeMode";
            break;
        case 0x10102c0:
            ns = Android_NS;
            name = "immersive";
            break;
        case 0x10103aa:
            ns = Android_NS;
            name = "importantForAccessibility";
            break;
        case 0x1010177:
            ns = Android_NS;
            name = "inAnimation";
            break;
        case 0x101015f:
            ns = Android_NS;
            name = "includeFontPadding";
            break;
        case 0x101026e:
            ns = Android_NS;
            name = "includeInGlobalSearch";
            break;
        case 0x1010139:
            ns = Android_NS;
            name = "indeterminate";
            break;
        case 0x101013e:
            ns = Android_NS;
            name = "indeterminateBehavior";
            break;
        case 0x101013b:
            ns = Android_NS;
            name = "indeterminateDrawable";
            break;
        case 0x101013d:
            ns = Android_NS;
            name = "indeterminateDuration";
            break;
        case 0x101013a:
            ns = Android_NS;
            name = "indeterminateOnly";
            break;
        case 0x1010318:
            ns = Android_NS;
            name = "indeterminateProgressStyle";
            break;
        case 0x101010d:
            ns = Android_NS;
            name = "indicatorLeft";
            break;
        case 0x101010e:
            ns = Android_NS;
            name = "indicatorRight";
            break;
        case 0x10100f3:
            ns = Android_NS;
            name = "inflatedId";
            break;
        case 0x101001a:
            ns = Android_NS;
            name = "initOrder";
            break;
        case 0x10103c2:
            ns = Android_NS;
            name = "initialKeyguardLayout";
            break;
        case 0x1010251:
            ns = Android_NS;
            name = "initialLayout";
            break;
        case 0x101025f:
            ns = Android_NS;
            name = "innerRadius";
            break;
        case 0x101019b:
            ns = Android_NS;
            name = "innerRadiusRatio";
            break;
        case 0x1010168:
            ns = Android_NS;
            name = "inputMethod";
            break;
        case 0x1010220:
            ns = Android_NS;
            name = "inputType";
            break;
        case 0x10101ba:
            ns = Android_NS;
            name = "insetBottom";
            break;
        case 0x10101b7:
            ns = Android_NS;
            name = "insetLeft";
            break;
        case 0x10101b8:
            ns = Android_NS;
            name = "insetRight";
            break;
        case 0x10101b9:
            ns = Android_NS;
            name = "insetTop";
            break;
        case 0x10102b7:
            ns = Android_NS;
            name = "installLocation";
            break;
        case 0x1010141:
            ns = Android_NS;
            name = "interpolator";
            break;
        case 0x1010333:
            ns = Android_NS;
            name = "isAlwaysSyncable";
            break;
        case 0x101037f:
            ns = Android_NS;
            name = "isAuxiliary";
            break;
        case 0x1010221:
            ns = Android_NS;
            name = "isDefault";
            break;
        case 0x1010147:
            ns = Android_NS;
            name = "isIndicator";
            break;
        case 0x1010246:
            ns = Android_NS;
            name = "isModifier";
            break;
        case 0x1010248:
            ns = Android_NS;
            name = "isRepeatable";
            break;
        case 0x101024e:
            ns = Android_NS;
            name = "isScrollContainer";
            break;
        case 0x1010247:
            ns = Android_NS;
            name = "isSticky";
            break;
        case 0x10103a9:
            ns = Android_NS;
            name = "isolatedProcess";
            break;
        case 0x1010130:
            ns = Android_NS;
            name = "itemBackground";
            break;
        case 0x1010131:
            ns = Android_NS;
            name = "itemIconDisabledAlpha";
            break;
        case 0x101032d:
            ns = Android_NS;
            name = "itemPadding";
            break;
        case 0x101012c:
            ns = Android_NS;
            name = "itemTextAppearance";
            break;
        case 0x1010216:
            ns = Android_NS;
            name = "keepScreenOn";
            break;
        case 0x10101e8:
            ns = Android_NS;
            name = "key";
            break;
        case 0x1010233:
            ns = Android_NS;
            name = "keyBackground";
            break;
        case 0x1010245:
            ns = Android_NS;
            name = "keyEdgeFlags";
            break;
        case 0x101023e:
            ns = Android_NS;
            name = "keyHeight";
            break;
        case 0x101024c:
            ns = Android_NS;
            name = "keyIcon";
            break;
        case 0x101024b:
            ns = Android_NS;
            name = "keyLabel";
            break;
        case 0x101024a:
            ns = Android_NS;
            name = "keyOutputText";
            break;
        case 0x1010239:
            ns = Android_NS;
            name = "keyPreviewHeight";
            break;
        case 0x1010237:
            ns = Android_NS;
            name = "keyPreviewLayout";
            break;
        case 0x1010238:
            ns = Android_NS;
            name = "keyPreviewOffset";
            break;
        case 0x1010236:
            ns = Android_NS;
            name = "keyTextColor";
            break;
        case 0x1010234:
            ns = Android_NS;
            name = "keyTextSize";
            break;
        case 0x101023d:
            ns = Android_NS;
            name = "keyWidth";
            break;
        case 0x10103ab:
            ns = Android_NS;
            name = "keyboardLayout";
            break;
        case 0x101024d:
            ns = Android_NS;
            name = "keyboardMode";
            break;
        case 0x10100c5:
            ns = Android_NS;
            name = "keycode";
            break;
        case 0x101029c:
            ns = Android_NS;
            name = "killAfterRestore";
            break;
        case 0x1010001:
            ns = Android_NS;
            name = "label";
            break;
        case 0x10103c6:
            ns = Android_NS;
            name = "labelFor";
            break;
        case 0x1010235:
            ns = Android_NS;
            name = "labelTextSize";
            break;
        case 0x101035a:
            ns = Android_NS;
            name = "largeHeap";
            break;
        case 0x1010286:
            ns = Android_NS;
            name = "largeScreens";
            break;
        case 0x1010366:
            ns = Android_NS;
            name = "largestWidthLimitDp";
            break;
        case 0x101001d:
            ns = Android_NS;
            name = "launchMode";
            break;
        case 0x1010354:
            ns = Android_NS;
            name = "layerType";
            break;
        case 0x10100f2:
            ns = Android_NS;
            name = "layout";
            break;
        case 0x10100ec:
            ns = Android_NS;
            name = "layoutAnimation";
            break;
        case 0x10103b2:
            ns = Android_NS;
            name = "layoutDirection";
            break;
        case 0x1010184:
            ns = Android_NS;
            name = "layout_above";
            break;
        case 0x1010186:
            ns = Android_NS;
            name = "layout_alignBaseline";
            break;
        case 0x101018a:
            ns = Android_NS;
            name = "layout_alignBottom";
            break;
        case 0x10103ba:
            ns = Android_NS;
            name = "layout_alignEnd";
            break;
        case 0x1010187:
            ns = Android_NS;
            name = "layout_alignLeft";
            break;
        case 0x101018e:
            ns = Android_NS;
            name = "layout_alignParentBottom";
            break;
        case 0x10103bc:
            ns = Android_NS;
            name = "layout_alignParentEnd";
            break;
        case 0x101018b:
            ns = Android_NS;
            name = "layout_alignParentLeft";
            break;
        case 0x101018d:
            ns = Android_NS;
            name = "layout_alignParentRight";
            break;
        case 0x10103bb:
            ns = Android_NS;
            name = "layout_alignParentStart";
            break;
        case 0x101018c:
            ns = Android_NS;
            name = "layout_alignParentTop";
            break;
        case 0x1010189:
            ns = Android_NS;
            name = "layout_alignRight";
            break;
        case 0x10103b9:
            ns = Android_NS;
            name = "layout_alignStart";
            break;
        case 0x1010188:
            ns = Android_NS;
            name = "layout_alignTop";
            break;
        case 0x1010192:
            ns = Android_NS;
            name = "layout_alignWithParentIfMissing";
            break;
        case 0x1010185:
            ns = Android_NS;
            name = "layout_below";
            break;
        case 0x1010190:
            ns = Android_NS;
            name = "layout_centerHorizontal";
            break;
        case 0x101018f:
            ns = Android_NS;
            name = "layout_centerInParent";
            break;
        case 0x1010191:
            ns = Android_NS;
            name = "layout_centerVertical";
            break;
        case 0x101014c:
            ns = Android_NS;
            name = "layout_column";
            break;
        case 0x101037d:
            ns = Android_NS;
            name = "layout_columnSpan";
            break;
        case 0x10100b3:
            ns = Android_NS;
            name = "layout_gravity";
            break;
        case 0x10100f5:
            ns = Android_NS;
            name = "layout_height";
            break;
        case 0x10100f6:
            ns = Android_NS;
            name = "layout_margin";
            break;
        case 0x10100fa:
            ns = Android_NS;
            name = "layout_marginBottom";
            break;
        case 0x10103b6:
            ns = Android_NS;
            name = "layout_marginEnd";
            break;
        case 0x10100f7:
            ns = Android_NS;
            name = "layout_marginLeft";
            break;
        case 0x10100f9:
            ns = Android_NS;
            name = "layout_marginRight";
            break;
        case 0x10103b5:
            ns = Android_NS;
            name = "layout_marginStart";
            break;
        case 0x10100f8:
            ns = Android_NS;
            name = "layout_marginTop";
            break;
        case 0x101037b:
            ns = Android_NS;
            name = "layout_row";
            break;
        case 0x101037c:
            ns = Android_NS;
            name = "layout_rowSpan";
            break;
        case 0x1010193:
            ns = Android_NS;
            name = "layout_scale";
            break;
        case 0x101014d:
            ns = Android_NS;
            name = "layout_span";
            break;
        case 0x10103b8:
            ns = Android_NS;
            name = "layout_toEndOf";
            break;
        case 0x1010182:
            ns = Android_NS;
            name = "layout_toLeftOf";
            break;
        case 0x1010183:
            ns = Android_NS;
            name = "layout_toRightOf";
            break;
        case 0x10103b7:
            ns = Android_NS;
            name = "layout_toStartOf";
            break;
        case 0x1010181:
            ns = Android_NS;
            name = "layout_weight";
            break;
        case 0x10100f4:
            ns = Android_NS;
            name = "layout_width";
            break;
        case 0x101017f:
            ns = Android_NS;
            name = "layout_x";
            break;
        case 0x1010180:
            ns = Android_NS;
            name = "layout_y";
            break;
        case 0x10101ad:
            ns = Android_NS;
            name = "left";
            break;
        case 0x1010217:
            ns = Android_NS;
            name = "lineSpacingExtra";
            break;
        case 0x1010218:
            ns = Android_NS;
            name = "lineSpacingMultiplier";
            break;
        case 0x1010154:
            ns = Android_NS;
            name = "lines";
            break;
        case 0x10100b1:
            ns = Android_NS;
            name = "linksClickable";
            break;
        case 0x10102f0:
            ns = Android_NS;
            name = "listChoiceBackgroundIndicator";
            break;
        case 0x101021a:
            ns = Android_NS;
            name = "listChoiceIndicatorMultiple";
            break;
        case 0x1010219:
            ns = Android_NS;
            name = "listChoiceIndicatorSingle";
            break;
        case 0x1010214:
            ns = Android_NS;
            name = "listDivider";
            break;
        case 0x1010305:
            ns = Android_NS;
            name = "listDividerAlertDialog";
            break;
        case 0x10102ff:
            ns = Android_NS;
            name = "listPopupWindowStyle";
            break;
        case 0x101004d:
            ns = Android_NS;
            name = "listPreferredItemHeight";
            break;
        case 0x1010386:
            ns = Android_NS;
            name = "listPreferredItemHeightLarge";
            break;
        case 0x1010387:
            ns = Android_NS;
            name = "listPreferredItemHeightSmall";
            break;
        case 0x10103be:
            ns = Android_NS;
            name = "listPreferredItemPaddingEnd";
            break;
        case 0x10103a3:
            ns = Android_NS;
            name = "listPreferredItemPaddingLeft";
            break;
        case 0x10103a4:
            ns = Android_NS;
            name = "listPreferredItemPaddingRight";
            break;
        case 0x10103bd:
            ns = Android_NS;
            name = "listPreferredItemPaddingStart";
            break;
        case 0x10100fb:
            ns = Android_NS;
            name = "listSelector";
            break;
        case 0x1010208:
            ns = Android_NS;
            name = "listSeparatorTextViewStyle";
            break;
        case 0x1010074:
            ns = Android_NS;
            name = "listViewStyle";
            break;
        case 0x1010075:
            ns = Android_NS;
            name = "listViewWhiteStyle";
            break;
        case 0x10102be:
            ns = Android_NS;
            name = "logo";
            break;
        case 0x10100e6:
            ns = Android_NS;
            name = "longClickable";
            break;
        case 0x1010307:
            ns = Android_NS;
            name = "loopViews";
            break;
        case 0x1010004:
            ns = Android_NS;
            name = "manageSpaceActivity";
            break;
        case 0x101008a:
            ns = Android_NS;
            name = "mapViewStyle";
            break;
        case 0x101021d:
            ns = Android_NS;
            name = "marqueeRepeatLimit";
            break;
        case 0x1010136:
            ns = Android_NS;
            name = "max";
            break;
        case 0x1010340:
            ns = Android_NS;
            name = "maxDate";
            break;
        case 0x1010157:
            ns = Android_NS;
            name = "maxEms";
            break;
        case 0x1010120:
            ns = Android_NS;
            name = "maxHeight";
            break;
        case 0x1010134:
            ns = Android_NS;
            name = "maxItemsPerRow";
            break;
        case 0x1010160:
            ns = Android_NS;
            name = "maxLength";
            break;
        case 0x10101b2:
            ns = Android_NS;
            name = "maxLevel";
            break;
        case 0x1010153:
            ns = Android_NS;
            name = "maxLines";
            break;
        case 0x1010133:
            ns = Android_NS;
            name = "maxRows";
            break;
        case 0x1010271:
            ns = Android_NS;
            name = "maxSdkVersion";
            break;
        case 0x101011f:
            ns = Android_NS;
            name = "maxWidth";
            break;
        case 0x101010a:
            ns = Android_NS;
            name = "measureAllChildren";
            break;
        case 0x10102d4:
            ns = Android_NS;
            name = "measureWithLargestChild";
            break;
        case 0x10103ad:
            ns = Android_NS;
            name = "mediaRouteButtonStyle";
            break;
        case 0x10103ae:
            ns = Android_NS;
            name = "mediaRouteTypes";
            break;
        case 0x10101de:
            ns = Android_NS;
            name = "menuCategory";
            break;
        case 0x1010026:
            ns = Android_NS;
            name = "mimeType";
            break;
        case 0x101033f:
            ns = Android_NS;
            name = "minDate";
            break;
        case 0x101015a:
            ns = Android_NS;
            name = "minEms";
            break;
        case 0x1010140:
            ns = Android_NS;
            name = "minHeight";
            break;
        case 0x10101b1:
            ns = Android_NS;
            name = "minLevel";
            break;
        case 0x1010156:
            ns = Android_NS;
            name = "minLines";
            break;
        case 0x1010396:
            ns = Android_NS;
            name = "minResizeHeight";
            break;
        case 0x1010395:
            ns = Android_NS;
            name = "minResizeWidth";
            break;
        case 0x101020c:
            ns = Android_NS;
            name = "minSdkVersion";
            break;
        case 0x101013f:
            ns = Android_NS;
            name = "minWidth";
            break;
        case 0x101017e:
            ns = Android_NS;
            name = "mode";
            break;
        case 0x1010135:
            ns = Android_NS;
            name = "moreIcon";
            break;
        case 0x1010013:
            ns = Android_NS;
            name = "multiprocess";
            break;
        case 0x1010003:
            ns = Android_NS;
            name = "name";
            break;
        case 0x10102cf:
            ns = Android_NS;
            name = "navigationMode";
            break;
        case 0x10101f6:
            ns = Android_NS;
            name = "negativeButtonText";
            break;
        case 0x10100e4:
            ns = Android_NS;
            name = "nextFocusDown";
            break;
        case 0x101033c:
            ns = Android_NS;
            name = "nextFocusForward";
            break;
        case 0x10100e1:
            ns = Android_NS;
            name = "nextFocusLeft";
            break;
        case 0x10100e2:
            ns = Android_NS;
            name = "nextFocusRight";
            break;
        case 0x10100e3:
            ns = Android_NS;
            name = "nextFocusUp";
            break;
        case 0x101022d:
            ns = Android_NS;
            name = "noHistory";
            break;
        case 0x1010285:
            ns = Android_NS;
            name = "normalScreens";
            break;
        case 0x1010383:
            ns = Android_NS;
            name = "notificationTimeout";
            break;
        case 0x1010118:
            ns = Android_NS;
            name = "numColumns";
            break;
        case 0x1010144:
            ns = Android_NS;
            name = "numStars";
            break;
        case 0x1010165:
            ns = Android_NS;
            name = "numeric";
            break;
        case 0x10101e4:
            ns = Android_NS;
            name = "numericShortcut";
            break;
        case 0x101026f:
            ns = Android_NS;
            name = "onClick";
            break;
        case 0x1010197:
            ns = Android_NS;
            name = "oneshot";
            break;
        case 0x101031e:
            ns = Android_NS;
            name = "opacity";
            break;
        case 0x10101ea:
            ns = Android_NS;
            name = "order";
            break;
        case 0x10101df:
            ns = Android_NS;
            name = "orderInCategory";
            break;
        case 0x10102e2:
            ns = Android_NS;
            name = "ordering";
            break;
        case 0x10101e7:
            ns = Android_NS;
            name = "orderingFromXml";
            break;
        case 0x10100c4:
            ns = Android_NS;
            name = "orientation";
            break;
        case 0x1010178:
            ns = Android_NS;
            name = "outAnimation";
            break;
        case 0x10102c3:
            ns = Android_NS;
            name = "overScrollFooter";
            break;
        case 0x10102c2:
            ns = Android_NS;
            name = "overScrollHeader";
            break;
        case 0x10102c1:
            ns = Android_NS;
            name = "overScrollMode";
            break;
        case 0x10103a2:
            ns = Android_NS;
            name = "overridesImplicitlyEnabledSubtype";
            break;
        case 0x1010381:
            ns = Android_NS;
            name = "packageNames";
            break;
        case 0x10100d5:
            ns = Android_NS;
            name = "padding";
            break;
        case 0x10100d9:
            ns = Android_NS;
            name = "paddingBottom";
            break;
        case 0x10103b4:
            ns = Android_NS;
            name = "paddingEnd";
            break;
        case 0x10100d6:
            ns = Android_NS;
            name = "paddingLeft";
            break;
        case 0x10100d8:
            ns = Android_NS;
            name = "paddingRight";
            break;
        case 0x10103b3:
            ns = Android_NS;
            name = "paddingStart";
            break;
        case 0x10100d7:
            ns = Android_NS;
            name = "paddingTop";
            break;
        case 0x101005e:
            ns = Android_NS;
            name = "panelBackground";
            break;
        case 0x1010061:
            ns = Android_NS;
            name = "panelColorBackground";
            break;
        case 0x1010060:
            ns = Android_NS;
            name = "panelColorForeground";
            break;
        case 0x101005f:
            ns = Android_NS;
            name = "panelFullBackground";
            break;
        case 0x1010062:
            ns = Android_NS;
            name = "panelTextAppearance";
            break;
        case 0x10103a7:
            ns = Android_NS;
            name = "parentActivityName";
            break;
        case 0x101015c:
            ns = Android_NS;
            name = "password";
            break;
        case 0x101002a:
            ns = Android_NS;
            name = "path";
            break;
        case 0x101002c:
            ns = Android_NS;
            name = "pathPattern";
            break;
        case 0x101002b:
            ns = Android_NS;
            name = "pathPrefix";
            break;
        case 0x1010006:
            ns = Android_NS;
            name = "permission";
            break;
        case 0x10103c7:
            ns = Android_NS;
            name = "permissionFlags";
            break;
        case 0x101000a:
            ns = Android_NS;
            name = "permissionGroup";
            break;
        case 0x10103c5:
            ns = Android_NS;
            name = "permissionGroupFlags";
            break;
        case 0x101000d:
            ns = Android_NS;
            name = "persistent";
            break;
        case 0x10100ee:
            ns = Android_NS;
            name = "persistentDrawingCache";
            break;
        case 0x1010167:
            ns = Android_NS;
            name = "phoneNumber";
            break;
        case 0x10101b5:
            ns = Android_NS;
            name = "pivotX";
            break;
        case 0x10101b6:
            ns = Android_NS;
            name = "pivotY";
            break;
        case 0x10102c9:
            ns = Android_NS;
            name = "popupAnimationStyle";
            break;
        case 0x1010176:
            ns = Android_NS;
            name = "popupBackground";
            break;
        case 0x1010244:
            ns = Android_NS;
            name = "popupCharacters";
            break;
        case 0x1010243:
            ns = Android_NS;
            name = "popupKeyboard";
            break;
        case 0x101023b:
            ns = Android_NS;
            name = "popupLayout";
            break;
        case 0x1010300:
            ns = Android_NS;
            name = "popupMenuStyle";
            break;
        case 0x1010076:
            ns = Android_NS;
            name = "popupWindowStyle";
            break;
        case 0x1010029:
            ns = Android_NS;
            name = "port";
            break;
        case 0x10101f5:
            ns = Android_NS;
            name = "positiveButtonText";
            break;
        case 0x101008c:
            ns = Android_NS;
            name = "preferenceCategoryStyle";
            break;
        case 0x101008d:
            ns = Android_NS;
            name = "preferenceInformationStyle";
            break;
        case 0x1010094:
            ns = Android_NS;
            name = "preferenceLayoutChild";
            break;
        case 0x101008b:
            ns = Android_NS;
            name = "preferenceScreenStyle";
            break;
        case 0x101008e:
            ns = Android_NS;
            name = "preferenceStyle";
            break;
        case 0x10103c0:
            ns = Android_NS;
            name = "presentationTheme";
            break;
        case 0x10102da:
            ns = Android_NS;
            name = "previewImage";
            break;
        case 0x101001c:
            ns = Android_NS;
            name = "priority";
            break;
        case 0x1010223:
            ns = Android_NS;
            name = "privateImeOptions";
            break;
        case 0x1010011:
            ns = Android_NS;
            name = "process";
            break;
        case 0x1010137:
            ns = Android_NS;
            name = "progress";
            break;
        case 0x1010319:
            ns = Android_NS;
            name = "progressBarPadding";
            break;
        case 0x1010077:
            ns = Android_NS;
            name = "progressBarStyle";
            break;
        case 0x1010078:
            ns = Android_NS;
            name = "progressBarStyleHorizontal";
            break;
        case 0x1010287:
            ns = Android_NS;
            name = "progressBarStyleInverse";
            break;
        case 0x101007a:
            ns = Android_NS;
            name = "progressBarStyleLarge";
            break;
        case 0x1010289:
            ns = Android_NS;
            name = "progressBarStyleLargeInverse";
            break;
        case 0x1010079:
            ns = Android_NS;
            name = "progressBarStyleSmall";
            break;
        case 0x1010288:
            ns = Android_NS;
            name = "progressBarStyleSmallInverse";
            break;
        case 0x101020f:
            ns = Android_NS;
            name = "progressBarStyleSmallTitle";
            break;
        case 0x101013c:
            ns = Android_NS;
            name = "progressDrawable";
            break;
        case 0x101017b:
            ns = Android_NS;
            name = "prompt";
            break;
        case 0x10102e1:
            ns = Android_NS;
            name = "propertyName";
            break;
        case 0x1010009:
            ns = Android_NS;
            name = "protectionLevel";
            break;
        case 0x10103a6:
            ns = Android_NS;
            name = "publicKey";
            break;
        case 0x10101db:
            ns = Android_NS;
            name = "queryActionMsg";
            break;
        case 0x1010282:
            ns = Android_NS;
            name = "queryAfterZeroResults";
            break;
        case 0x1010358:
            ns = Android_NS;
            name = "queryHint";
            break;
        case 0x10102b3:
            ns = Android_NS;
            name = "quickContactBadgeStyleSmallWindowLarge";
            break;
        case 0x10102b2:
            ns = Android_NS;
            name = "quickContactBadgeStyleSmallWindowMedium";
            break;
        case 0x10102b1:
            ns = Android_NS;
            name = "quickContactBadgeStyleSmallWindowSmall";
            break;
        case 0x10102b0:
            ns = Android_NS;
            name = "quickContactBadgeStyleWindowLarge";
            break;
        case 0x10102af:
            ns = Android_NS;
            name = "quickContactBadgeStyleWindowMedium";
            break;
        case 0x10102ae:
            ns = Android_NS;
            name = "quickContactBadgeStyleWindowSmall";
            break;
        case 0x101007e:
            ns = Android_NS;
            name = "radioButtonStyle";
            break;
        case 0x10101a8:
            ns = Android_NS;
            name = "radius";
            break;
        case 0x1010145:
            ns = Android_NS;
            name = "rating";
            break;
        case 0x101007c:
            ns = Android_NS;
            name = "ratingBarStyle";
            break;
        case 0x1010210:
            ns = Android_NS;
            name = "ratingBarStyleIndicator";
            break;
        case 0x101007d:
            ns = Android_NS;
            name = "ratingBarStyleSmall";
            break;
        case 0x1010007:
            ns = Android_NS;
            name = "readPermission";
            break;
        case 0x10101bf:
            ns = Android_NS;
            name = "repeatCount";
            break;
        case 0x10101c0:
            ns = Android_NS;
            name = "repeatMode";
            break;
        case 0x1010232:
            ns = Android_NS;
            name = "reqFiveWayNav";
            break;
        case 0x1010229:
            ns = Android_NS;
            name = "reqHardKeyboard";
            break;
        case 0x1010228:
            ns = Android_NS;
            name = "reqKeyboardType";
            break;
        case 0x101022a:
            ns = Android_NS;
            name = "reqNavigation";
            break;
        case 0x1010227:
            ns = Android_NS;
            name = "reqTouchScreen";
            break;
        case 0x101028e:
            ns = Android_NS;
            name = "required";
            break;
        case 0x10103a5:
            ns = Android_NS;
            name = "requiresFadingEdge";
            break;
        case 0x1010364:
            ns = Android_NS;
            name = "requiresSmallestWidthDp";
            break;
        case 0x1010363:
            ns = Android_NS;
            name = "resizeMode";
            break;
        case 0x101028d:
            ns = Android_NS;
            name = "resizeable";
            break;
        case 0x1010025:
            ns = Android_NS;
            name = "resource";
            break;
        case 0x10102ba:
            ns = Android_NS;
            name = "restoreAnyVersion";
            break;
        case 0x101029d:
            ns = Android_NS;
            name = "restoreNeedsApplication";
            break;
        case 0x10101af:
            ns = Android_NS;
            name = "right";
            break;
        case 0x1010093:
            ns = Android_NS;
            name = "ringtonePreferenceStyle";
            break;
        case 0x10101f9:
            ns = Android_NS;
            name = "ringtoneType";
            break;
        case 0x1010326:
            ns = Android_NS;
            name = "rotation";
            break;
        case 0x1010327:
            ns = Android_NS;
            name = "rotationX";
            break;
        case 0x1010328:
            ns = Android_NS;
            name = "rotationY";
            break;
        case 0x1010375:
            ns = Android_NS;
            name = "rowCount";
            break;
        case 0x10101d0:
            ns = Android_NS;
            name = "rowDelay";
            break;
        case 0x1010241:
            ns = Android_NS;
            name = "rowEdgeFlags";
            break;
        case 0x1010132:
            ns = Android_NS;
            name = "rowHeight";
            break;
        case 0x1010376:
            ns = Android_NS;
            name = "rowOrderPreserved";
            break;
        case 0x10100e7:
            ns = Android_NS;
            name = "saveEnabled";
            break;
        case 0x10101fe:
            ns = Android_NS;
            name = "scaleGravity";
            break;
        case 0x10101fd:
            ns = Android_NS;
            name = "scaleHeight";
            break;
        case 0x101011d:
            ns = Android_NS;
            name = "scaleType";
            break;
        case 0x10101fc:
            ns = Android_NS;
            name = "scaleWidth";
            break;
        case 0x1010324:
            ns = Android_NS;
            name = "scaleX";
            break;
        case 0x1010325:
            ns = Android_NS;
            name = "scaleY";
            break;
        case 0x1010027:
            ns = Android_NS;
            name = "scheme";
            break;
        case 0x10102cb:
            ns = Android_NS;
            name = "screenDensity";
            break;
        case 0x101001e:
            ns = Android_NS;
            name = "screenOrientation";
            break;
        case 0x10102ca:
            ns = Android_NS;
            name = "screenSize";
            break;
        case 0x101015b:
            ns = Android_NS;
            name = "scrollHorizontally";
            break;
        case 0x1010080:
            ns = Android_NS;
            name = "scrollViewStyle";
            break;
        case 0x10100d2:
            ns = Android_NS;
            name = "scrollX";
            break;
        case 0x10100d3:
            ns = Android_NS;
            name = "scrollY";
            break;
        case 0x1010068:
            ns = Android_NS;
            name = "scrollbarAlwaysDrawHorizontalTrack";
            break;
        case 0x1010069:
            ns = Android_NS;
            name = "scrollbarAlwaysDrawVerticalTrack";
            break;
        case 0x10102a9:
            ns = Android_NS;
            name = "scrollbarDefaultDelayBeforeFade";
            break;
        case 0x10102a8:
            ns = Android_NS;
            name = "scrollbarFadeDuration";
            break;
        case 0x1010063:
            ns = Android_NS;
            name = "scrollbarSize";
            break;
        case 0x101007f:
            ns = Android_NS;
            name = "scrollbarStyle";
            break;
        case 0x1010064:
            ns = Android_NS;
            name = "scrollbarThumbHorizontal";
            break;
        case 0x1010065:
            ns = Android_NS;
            name = "scrollbarThumbVertical";
            break;
        case 0x1010066:
            ns = Android_NS;
            name = "scrollbarTrackHorizontal";
            break;
        case 0x1010067:
            ns = Android_NS;
            name = "scrollbarTrackVertical";
            break;
        case 0x10100de:
            ns = Android_NS;
            name = "scrollbars";
            break;
        case 0x10100fe:
            ns = Android_NS;
            name = "scrollingCache";
            break;
        case 0x1010205:
            ns = Android_NS;
            name = "searchButtonText";
            break;
        case 0x10101d5:
            ns = Android_NS;
            name = "searchMode";
            break;
        case 0x101028a:
            ns = Android_NS;
            name = "searchSettingsDescription";
            break;
        case 0x10101d6:
            ns = Android_NS;
            name = "searchSuggestAuthority";
            break;
        case 0x10101d9:
            ns = Android_NS;
            name = "searchSuggestIntentAction";
            break;
        case 0x10101da:
            ns = Android_NS;
            name = "searchSuggestIntentData";
            break;
        case 0x10101d7:
            ns = Android_NS;
            name = "searchSuggestPath";
            break;
        case 0x10101d8:
            ns = Android_NS;
            name = "searchSuggestSelection";
            break;
        case 0x101026d:
            ns = Android_NS;
            name = "searchSuggestThreshold";
            break;
        case 0x1010138:
            ns = Android_NS;
            name = "secondaryProgress";
            break;
        case 0x101007b:
            ns = Android_NS;
            name = "seekBarStyle";
            break;
        case 0x1010330:
            ns = Android_NS;
            name = "segmentedButtonStyle";
            break;
        case 0x101015e:
            ns = Android_NS;
            name = "selectAllOnFocus";
            break;
        case 0x10101e6:
            ns = Android_NS;
            name = "selectable";
            break;
        case 0x101030e:
            ns = Android_NS;
            name = "selectableItemBackground";
            break;
        case 0x1010347:
            ns = Android_NS;
            name = "selectedDateVerticalBar";
            break;
        case 0x1010342:
            ns = Android_NS;
            name = "selectedWeekBackgroundColor";
            break;
        case 0x1010225:
            ns = Android_NS;
            name = "settingsActivity";
            break;
        case 0x1010161:
            ns = Android_NS;
            name = "shadowColor";
            break;
        case 0x1010162:
            ns = Android_NS;
            name = "shadowDx";
            break;
        case 0x1010163:
            ns = Android_NS;
            name = "shadowDy";
            break;
        case 0x1010164:
            ns = Android_NS;
            name = "shadowRadius";
            break;
        case 0x101019a:
            ns = Android_NS;
            name = "shape";
            break;
        case 0x10101bb:
            ns = Android_NS;
            name = "shareInterpolator";
            break;
        case 0x101000b:
            ns = Android_NS;
            name = "sharedUserId";
            break;
        case 0x1010261:
            ns = Android_NS;
            name = "sharedUserLabel";
            break;
        case 0x10101ee:
            ns = Android_NS;
            name = "shouldDisableView";
            break;
        case 0x10102d9:
            ns = Android_NS;
            name = "showAsAction";
            break;
        case 0x10101fa:
            ns = Android_NS;
            name = "showDefault";
            break;
        case 0x1010329:
            ns = Android_NS;
            name = "showDividers";
            break;
        case 0x10103c9:
            ns = Android_NS;
            name = "showOnLockScreen";
            break;
        case 0x10101fb:
            ns = Android_NS;
            name = "showSilent";
            break;
        case 0x101033e:
            ns = Android_NS;
            name = "showWeekNumber";
            break;
        case 0x1010341:
            ns = Android_NS;
            name = "shownWeekCount";
            break;
        case 0x101014a:
            ns = Android_NS;
            name = "shrinkColumns";
            break;
        case 0x101015d:
            ns = Android_NS;
            name = "singleLine";
            break;
        case 0x10103bf:
            ns = Android_NS;
            name = "singleUser";
            break;
        case 0x101029e:
            ns = Android_NS;
            name = "smallIcon";
            break;
        case 0x1010284:
            ns = Android_NS;
            name = "smallScreens";
            break;
        case 0x1010231:
            ns = Android_NS;
            name = "smoothScrollbar";
            break;
        case 0x1010215:
            ns = Android_NS;
            name = "soundEffectsEnabled";
            break;
        case 0x1010113:
            ns = Android_NS;
            name = "spacing";
            break;
        case 0x1010087:
            ns = Android_NS;
            name = "spinnerDropDownItemStyle";
            break;
        case 0x1010089:
            ns = Android_NS;
            name = "spinnerItemStyle";
            break;
        case 0x10102f1:
            ns = Android_NS;
            name = "spinnerMode";
            break;
        case 0x1010081:
            ns = Android_NS;
            name = "spinnerStyle";
            break;
        case 0x101034b:
            ns = Android_NS;
            name = "spinnersShown";
            break;
        case 0x10102ef:
            ns = Android_NS;
            name = "splitMotionEvents";
            break;
        case 0x1010119:
            ns = Android_NS;
            name = "src";
            break;
        case 0x10100fd:
            ns = Android_NS;
            name = "stackFromBottom";
            break;
        case 0x1010082:
            ns = Android_NS;
            name = "starStyle";
            break;
        case 0x101019d:
            ns = Android_NS;
            name = "startColor";
            break;
        case 0x10101be:
            ns = Android_NS;
            name = "startOffset";
            break;
        case 0x101017c:
            ns = Android_NS;
            name = "startYear";
            break;
        case 0x1010016:
            ns = Android_NS;
            name = "stateNotNeeded";
            break;
        case 0x10100aa:
            ns = Android_NS;
            name = "state_above_anchor";
            break;
        case 0x101031b:
            ns = Android_NS;
            name = "state_accelerated";
            break;
        case 0x10102fe:
            ns = Android_NS;
            name = "state_activated";
            break;
        case 0x10100a2:
            ns = Android_NS;
            name = "state_active";
            break;
        case 0x101009f:
            ns = Android_NS;
            name = "state_checkable";
            break;
        case 0x10100a0:
            ns = Android_NS;
            name = "state_checked";
            break;
        case 0x1010368:
            ns = Android_NS;
            name = "state_drag_can_accept";
            break;
        case 0x1010369:
            ns = Android_NS;
            name = "state_drag_hovered";
            break;
        case 0x10100a9:
            ns = Android_NS;
            name = "state_empty";
            break;
        case 0x101009e:
            ns = Android_NS;
            name = "state_enabled";
            break;
        case 0x10100a8:
            ns = Android_NS;
            name = "state_expanded";
            break;
        case 0x10100a4:
            ns = Android_NS;
            name = "state_first";
            break;
        case 0x101009c:
            ns = Android_NS;
            name = "state_focused";
            break;
        case 0x1010367:
            ns = Android_NS;
            name = "state_hovered";
            break;
        case 0x10100a6:
            ns = Android_NS;
            name = "state_last";
            break;
        case 0x101023c:
            ns = Android_NS;
            name = "state_long_pressable";
            break;
        case 0x10100a5:
            ns = Android_NS;
            name = "state_middle";
            break;
        case 0x101034d:
            ns = Android_NS;
            name = "state_multiline";
            break;
        case 0x10100a7:
            ns = Android_NS;
            name = "state_pressed";
            break;
        case 0x10100a1:
            ns = Android_NS;
            name = "state_selected";
            break;
        case 0x10100a3:
            ns = Android_NS;
            name = "state_single";
            break;
        case 0x101009d:
            ns = Android_NS;
            name = "state_window_focused";
            break;
        case 0x1010331:
            ns = Android_NS;
            name = "staticWallpaperPreview";
            break;
        case 0x1010146:
            ns = Android_NS;
            name = "stepSize";
            break;
        case 0x101036a:
            ns = Android_NS;
            name = "stopWithTask";
            break;
        case 0x1010209:
            ns = Android_NS;
            name = "streamType";
            break;
        case 0x1010149:
            ns = Android_NS;
            name = "stretchColumns";
            break;
        case 0x1010116:
            ns = Android_NS;
            name = "stretchMode";
            break;
        case 0x10102d1:
            ns = Android_NS;
            name = "subtitle";
            break;
        case 0x10102f9:
            ns = Android_NS;
            name = "subtitleTextStyle";
            break;
        case 0x101039a:
            ns = Android_NS;
            name = "subtypeExtraValue";
            break;
        case 0x10103c1:
            ns = Android_NS;
            name = "subtypeId";
            break;
        case 0x1010399:
            ns = Android_NS;
            name = "subtypeLocale";
            break;
        case 0x10101dc:
            ns = Android_NS;
            name = "suggestActionMsg";
            break;
        case 0x10101dd:
            ns = Android_NS;
            name = "suggestActionMsgColumn";
            break;
        case 0x10101e9:
            ns = Android_NS;
            name = "summary";
            break;
        case 0x10102a2:
            ns = Android_NS;
            name = "summaryColumn";
            break;
        case 0x10101f0:
            ns = Android_NS;
            name = "summaryOff";
            break;
        case 0x10101ef:
            ns = Android_NS;
            name = "summaryOn";
            break;
        case 0x10103af:
            ns = Android_NS;
            name = "supportsRtl";
            break;
        case 0x101029b:
            ns = Android_NS;
            name = "supportsUploading";
            break;
        case 0x1010370:
            ns = Android_NS;
            name = "switchMinWidth";
            break;
        case 0x1010371:
            ns = Android_NS;
            name = "switchPadding";
            break;
        case 0x101036d:
            ns = Android_NS;
            name = "switchPreferenceStyle";
            break;
        case 0x101036e:
            ns = Android_NS;
            name = "switchTextAppearance";
            break;
        case 0x101036c:
            ns = Android_NS;
            name = "switchTextOff";
            break;
        case 0x101036b:
            ns = Android_NS;
            name = "switchTextOn";
            break;
        case 0x1010019:
            ns = Android_NS;
            name = "syncable";
            break;
        case 0x10102bd:
            ns = Android_NS;
            name = "tabStripEnabled";
            break;
        case 0x10102bb:
            ns = Android_NS;
            name = "tabStripLeft";
            break;
        case 0x10102bc:
            ns = Android_NS;
            name = "tabStripRight";
            break;
        case 0x1010083:
            ns = Android_NS;
            name = "tabWidgetStyle";
            break;
        case 0x10100d1:
            ns = Android_NS;
            name = "tag";
            break;
        case 0x1010202:
            ns = Android_NS;
            name = "targetActivity";
            break;
        case 0x101002f:
            ns = Android_NS;
            name = "targetClass";
            break;
        case 0x10103a0:
            ns = Android_NS;
            name = "targetDescriptions";
            break;
        case 0x1010021:
            ns = Android_NS;
            name = "targetPackage";
            break;
        case 0x1010270:
            ns = Android_NS;
            name = "targetSdkVersion";
            break;
        case 0x1010012:
            ns = Android_NS;
            name = "taskAffinity";
            break;
        case 0x10100be:
            ns = Android_NS;
            name = "taskCloseEnterAnimation";
            break;
        case 0x10100bf:
            ns = Android_NS;
            name = "taskCloseExitAnimation";
            break;
        case 0x10100bc:
            ns = Android_NS;
            name = "taskOpenEnterAnimation";
            break;
        case 0x10100bd:
            ns = Android_NS;
            name = "taskOpenExitAnimation";
            break;
        case 0x10100c2:
            ns = Android_NS;
            name = "taskToBackEnterAnimation";
            break;
        case 0x10100c3:
            ns = Android_NS;
            name = "taskToBackExitAnimation";
            break;
        case 0x10100c0:
            ns = Android_NS;
            name = "taskToFrontEnterAnimation";
            break;
        case 0x10100c1:
            ns = Android_NS;
            name = "taskToFrontExitAnimation";
            break;
        case 0x101026a:
            ns = Android_NS;
            name = "tension";
            break;
        case 0x1010272:
            ns = Android_NS;
            name = "testOnly";
            break;
        case 0x101014f:
            ns = Android_NS;
            name = "text";
            break;
        case 0x10103b1:
            ns = Android_NS;
            name = "textAlignment";
            break;
        case 0x101038c:
            ns = Android_NS;
            name = "textAllCaps";
            break;
        case 0x1010034:
            ns = Android_NS;
            name = "textAppearance";
            break;
        case 0x1010207:
            ns = Android_NS;
            name = "textAppearanceButton";
            break;
        case 0x1010035:
            ns = Android_NS;
            name = "textAppearanceInverse";
            break;
        case 0x1010040:
            ns = Android_NS;
            name = "textAppearanceLarge";
            break;
        case 0x1010043:
            ns = Android_NS;
            name = "textAppearanceLargeInverse";
            break;
        case 0x1010301:
            ns = Android_NS;
            name = "textAppearanceLargePopupMenu";
            break;
        case 0x101039e:
            ns = Android_NS;
            name = "textAppearanceListItem";
            break;
        case 0x101039f:
            ns = Android_NS;
            name = "textAppearanceListItemSmall";
            break;
        case 0x1010041:
            ns = Android_NS;
            name = "textAppearanceMedium";
            break;
        case 0x1010044:
            ns = Android_NS;
            name = "textAppearanceMediumInverse";
            break;
        case 0x10102a0:
            ns = Android_NS;
            name = "textAppearanceSearchResultSubtitle";
            break;
        case 0x10102a1:
            ns = Android_NS;
            name = "textAppearanceSearchResultTitle";
            break;
        case 0x1010042:
            ns = Android_NS;
            name = "textAppearanceSmall";
            break;
        case 0x1010045:
            ns = Android_NS;
            name = "textAppearanceSmallInverse";
            break;
        case 0x1010302:
            ns = Android_NS;
            name = "textAppearanceSmallPopupMenu";
            break;
        case 0x1010046:
            ns = Android_NS;
            name = "textCheckMark";
            break;
        case 0x1010047:
            ns = Android_NS;
            name = "textCheckMarkInverse";
            break;
        case 0x1010098:
            ns = Android_NS;
            name = "textColor";
            break;
        case 0x1010306:
            ns = Android_NS;
            name = "textColorAlertDialogListItem";
            break;
        case 0x1010099:
            ns = Android_NS;
            name = "textColorHighlight";
            break;
        case 0x101034f:
            ns = Android_NS;
            name = "textColorHighlightInverse";
            break;
        case 0x101009a:
            ns = Android_NS;
            name = "textColorHint";
            break;
        case 0x101003f:
            ns = Android_NS;
            name = "textColorHintInverse";
            break;
        case 0x101009b:
            ns = Android_NS;
            name = "textColorLink";
            break;
        case 0x1010350:
            ns = Android_NS;
            name = "textColorLinkInverse";
            break;
        case 0x1010036:
            ns = Android_NS;
            name = "textColorPrimary";
            break;
        case 0x1010037:
            ns = Android_NS;
            name = "textColorPrimaryDisableOnly";
            break;
        case 0x1010039:
            ns = Android_NS;
            name = "textColorPrimaryInverse";
            break;
        case 0x101028b:
            ns = Android_NS;
            name = "textColorPrimaryInverseDisableOnly";
            break;
        case 0x101003d:
            ns = Android_NS;
            name = "textColorPrimaryInverseNoDisable";
            break;
        case 0x101003b:
            ns = Android_NS;
            name = "textColorPrimaryNoDisable";
            break;
        case 0x1010038:
            ns = Android_NS;
            name = "textColorSecondary";
            break;
        case 0x101003a:
            ns = Android_NS;
            name = "textColorSecondaryInverse";
            break;
        case 0x101003e:
            ns = Android_NS;
            name = "textColorSecondaryInverseNoDisable";
            break;
        case 0x101003c:
            ns = Android_NS;
            name = "textColorSecondaryNoDisable";
            break;
        case 0x1010212:
            ns = Android_NS;
            name = "textColorTertiary";
            break;
        case 0x1010213:
            ns = Android_NS;
            name = "textColorTertiaryInverse";
            break;
        case 0x1010362:
            ns = Android_NS;
            name = "textCursorDrawable";
            break;
        case 0x10103b0:
            ns = Android_NS;
            name = "textDirection";
            break;
        case 0x1010315:
            ns = Android_NS;
            name = "textEditNoPasteWindowLayout";
            break;
        case 0x1010314:
            ns = Android_NS;
            name = "textEditPasteWindowLayout";
            break;
        case 0x101035f:
            ns = Android_NS;
            name = "textEditSideNoPasteWindowLayout";
            break;
        case 0x101035e:
            ns = Android_NS;
            name = "textEditSidePasteWindowLayout";
            break;
        case 0x1010374:
            ns = Android_NS;
            name = "textEditSuggestionItemLayout";
            break;
        case 0x10100ff:
            ns = Android_NS;
            name = "textFilterEnabled";
            break;
        case 0x1010316:
            ns = Android_NS;
            name = "textIsSelectable";
            break;
        case 0x1010125:
            ns = Android_NS;
            name = "textOff";
            break;
        case 0x1010124:
            ns = Android_NS;
            name = "textOn";
            break;
        case 0x1010151:
            ns = Android_NS;
            name = "textScaleX";
            break;
        case 0x10102c7:
            ns = Android_NS;
            name = "textSelectHandle";
            break;
        case 0x10102c5:
            ns = Android_NS;
            name = "textSelectHandleLeft";
            break;
        case 0x10102c6:
            ns = Android_NS;
            name = "textSelectHandleRight";
            break;
        case 0x10102c8:
            ns = Android_NS;
            name = "textSelectHandleWindowStyle";
            break;
        case 0x1010095:
            ns = Android_NS;
            name = "textSize";
            break;
        case 0x1010097:
            ns = Android_NS;
            name = "textStyle";
            break;
        case 0x1010373:
            ns = Android_NS;
            name = "textSuggestionsWindowStyle";
            break;
        case 0x1010084:
            ns = Android_NS;
            name = "textViewStyle";
            break;
        case 0x1010000:
            ns = Android_NS;
            name = "theme";
            break;
        case 0x1010260:
            ns = Android_NS;
            name = "thickness";
            break;
        case 0x101019c:
            ns = Android_NS;
            name = "thicknessRatio";
            break;
        case 0x1010142:
            ns = Android_NS;
            name = "thumb";
            break;
        case 0x1010143:
            ns = Android_NS;
            name = "thumbOffset";
            break;
        case 0x1010372:
            ns = Android_NS;
            name = "thumbTextPadding";
            break;
        case 0x10102a5:
            ns = Android_NS;
            name = "thumbnail";
            break;
        case 0x1010201:
            ns = Android_NS;
            name = "tileMode";
            break;
        case 0x10103cc:
            ns = Android_NS;
            name = "timeZone";
            break;
        case 0x1010121:
            ns = Android_NS;
            name = "tint";
            break;
        case 0x10101e1:
            ns = Android_NS;
            name = "title";
            break;
        case 0x10101e2:
            ns = Android_NS;
            name = "titleCondensed";
            break;
        case 0x10102f8:
            ns = Android_NS;
            name = "titleTextStyle";
            break;
        case 0x10101cb:
            ns = Android_NS;
            name = "toAlpha";
            break;
        case 0x10101b4:
            ns = Android_NS;
            name = "toDegrees";
            break;
        case 0x10101c7:
            ns = Android_NS;
            name = "toXDelta";
            break;
        case 0x10101c3:
            ns = Android_NS;
            name = "toXScale";
            break;
        case 0x10101c9:
            ns = Android_NS;
            name = "toYDelta";
            break;
        case 0x10101c5:
            ns = Android_NS;
            name = "toYScale";
            break;
        case 0x10101ae:
            ns = Android_NS;
            name = "top";
            break;
        case 0x10100cb:
            ns = Android_NS;
            name = "topBright";
            break;
        case 0x10100c7:
            ns = Android_NS;
            name = "topDark";
            break;
        case 0x10101a9:
            ns = Android_NS;
            name = "topLeftRadius";
            break;
        case 0x1010258:
            ns = Android_NS;
            name = "topOffset";
            break;
        case 0x10101aa:
            ns = Android_NS;
            name = "topRightRadius";
            break;
        case 0x101036f:
            ns = Android_NS;
            name = "track";
            break;
        case 0x1010100:
            ns = Android_NS;
            name = "transcriptMode";
            break;
        case 0x1010320:
            ns = Android_NS;
            name = "transformPivotX";
            break;
        case 0x1010321:
            ns = Android_NS;
            name = "transformPivotY";
            break;
        case 0x1010322:
            ns = Android_NS;
            name = "translationX";
            break;
        case 0x1010323:
            ns = Android_NS;
            name = "translationY";
            break;
        case 0x10101a1:
            ns = Android_NS;
            name = "type";
            break;
        case 0x1010096:
            ns = Android_NS;
            name = "typeface";
            break;
        case 0x1010398:
            ns = Android_NS;
            name = "uiOptions";
            break;
        case 0x1010276:
            ns = Android_NS;
            name = "uncertainGestureColor";
            break;
        case 0x1010344:
            ns = Android_NS;
            name = "unfocusedMonthDateColor";
            break;
        case 0x101020e:
            ns = Android_NS;
            name = "unselectedAlpha";
            break;
        case 0x1010250:
            ns = Android_NS;
            name = "updatePeriodMillis";
            break;
        case 0x1010379:
            ns = Android_NS;
            name = "useDefaultMargins";
            break;
        case 0x1010310:
            ns = Android_NS;
            name = "useIntrinsicSizeAsMinimum";
            break;
        case 0x101019f:
            ns = Android_NS;
            name = "useLevel";
            break;
        case 0x1010291:
            ns = Android_NS;
            name = "userVisible";
            break;
        case 0x1010024:
            ns = Android_NS;
            name = "value";
            break;
        case 0x10102de:
            ns = Android_NS;
            name = "valueFrom";
            break;
        case 0x10102df:
            ns = Android_NS;
            name = "valueTo";
            break;
        case 0x10102e0:
            ns = Android_NS;
            name = "valueType";
            break;
        case 0x1010195:
            ns = Android_NS;
            name = "variablePadding";
            break;
        case 0x101021b:
            ns = Android_NS;
            name = "versionCode";
            break;
        case 0x101021c:
            ns = Android_NS;
            name = "versionName";
            break;
        case 0x101023a:
            ns = Android_NS;
            name = "verticalCorrection";
            break;
        case 0x101012e:
            ns = Android_NS;
            name = "verticalDivider";
            break;
        case 0x1010240:
            ns = Android_NS;
            name = "verticalGap";
            break;
        case 0x1010334:
            ns = Android_NS;
            name = "verticalScrollbarPosition";
            break;
        case 0x1010115:
            ns = Android_NS;
            name = "verticalSpacing";
            break;
        case 0x10100dc:
            ns = Android_NS;
            name = "visibility";
            break;
        case 0x1010194:
            ns = Android_NS;
            name = "visible";
            break;
        case 0x10102b8:
            ns = Android_NS;
            name = "vmSafeMode";
            break;
        case 0x1010255:
            ns = Android_NS;
            name = "voiceLanguage";
            break;
        case 0x1010253:
            ns = Android_NS;
            name = "voiceLanguageModel";
            break;
        case 0x1010256:
            ns = Android_NS;
            name = "voiceMaxResults";
            break;
        case 0x1010254:
            ns = Android_NS;
            name = "voicePromptText";
            break;
        case 0x1010252:
            ns = Android_NS;
            name = "voiceSearchMode";
            break;
        case 0x1010295:
            ns = Android_NS;
            name = "wallpaperCloseEnterAnimation";
            break;
        case 0x1010296:
            ns = Android_NS;
            name = "wallpaperCloseExitAnimation";
            break;
        case 0x1010299:
            ns = Android_NS;
            name = "wallpaperIntraCloseEnterAnimation";
            break;
        case 0x101029a:
            ns = Android_NS;
            name = "wallpaperIntraCloseExitAnimation";
            break;
        case 0x1010297:
            ns = Android_NS;
            name = "wallpaperIntraOpenEnterAnimation";
            break;
        case 0x1010298:
            ns = Android_NS;
            name = "wallpaperIntraOpenExitAnimation";
            break;
        case 0x1010293:
            ns = Android_NS;
            name = "wallpaperOpenEnterAnimation";
            break;
        case 0x1010294:
            ns = Android_NS;
            name = "wallpaperOpenExitAnimation";
            break;
        case 0x10102b9:
            ns = Android_NS;
            name = "webTextViewStyle";
            break;
        case 0x1010085:
            ns = Android_NS;
            name = "webViewStyle";
            break;
        case 0x1010348:
            ns = Android_NS;
            name = "weekDayTextAppearance";
            break;
        case 0x1010345:
            ns = Android_NS;
            name = "weekNumberColor";
            break;
        case 0x1010346:
            ns = Android_NS;
            name = "weekSeparatorLineColor";
            break;
        case 0x1010128:
            ns = Android_NS;
            name = "weightSum";
            break;
        case 0x10103c4:
            ns = Android_NS;
            name = "widgetCategory";
            break;
        case 0x10101eb:
            ns = Android_NS;
            name = "widgetLayout";
            break;
        case 0x1010159:
            ns = Android_NS;
            name = "width";
            break;
        case 0x10102cd:
            ns = Android_NS;
            name = "windowActionBar";
            break;
        case 0x10102e4:
            ns = Android_NS;
            name = "windowActionBarOverlay";
            break;
        case 0x10102dd:
            ns = Android_NS;
            name = "windowActionModeOverlay";
            break;
        case 0x10100ae:
            ns = Android_NS;
            name = "windowAnimationStyle";
            break;
        case 0x1010054:
            ns = Android_NS;
            name = "windowBackground";
            break;
        case 0x101035b:
            ns = Android_NS;
            name = "windowCloseOnTouchOutside";
            break;
        case 0x1010059:
            ns = Android_NS;
            name = "windowContentOverlay";
            break;
        case 0x1010222:
            ns = Android_NS;
            name = "windowDisablePreview";
            break;
        case 0x1010317:
            ns = Android_NS;
            name = "windowEnableSplitTouch";
            break;
        case 0x10100b4:
            ns = Android_NS;
            name = "windowEnterAnimation";
            break;
        case 0x10100b5:
            ns = Android_NS;
            name = "windowExitAnimation";
            break;
        case 0x1010055:
            ns = Android_NS;
            name = "windowFrame";
            break;
        case 0x101020d:
            ns = Android_NS;
            name = "windowFullscreen";
            break;
        case 0x10100b7:
            ns = Android_NS;
            name = "windowHideAnimation";
            break;
        case 0x1010057:
            ns = Android_NS;
            name = "windowIsFloating";
            break;
        case 0x1010058:
            ns = Android_NS;
            name = "windowIsTranslucent";
            break;
        case 0x1010356:
            ns = Android_NS;
            name = "windowMinWidthMajor";
            break;
        case 0x1010357:
            ns = Android_NS;
            name = "windowMinWidthMinor";
            break;
        case 0x101021e:
            ns = Android_NS;
            name = "windowNoDisplay";
            break;
        case 0x1010056:
            ns = Android_NS;
            name = "windowNoTitle";
            break;
        case 0x10100b6:
            ns = Android_NS;
            name = "windowShowAnimation";
            break;
        case 0x1010292:
            ns = Android_NS;
            name = "windowShowWallpaper";
            break;
        case 0x101022b:
            ns = Android_NS;
            name = "windowSoftInputMode";
            break;
        case 0x101005c:
            ns = Android_NS;
            name = "windowTitleBackgroundStyle";
            break;
        case 0x101005a:
            ns = Android_NS;
            name = "windowTitleSize";
            break;
        case 0x101005b:
            ns = Android_NS;
            name = "windowTitleStyle";
            break;
        case 0x1010008:
            ns = Android_NS;
            name = "writePermission";
            break;
        case 0x10100ac:
            ns = Android_NS;
            name = "x";
            break;
        case 0x10102bf:
            ns = Android_NS;
            name = "xlargeScreens";
            break;
        case 0x10100ad:
            ns = Android_NS;
            name = "y";
            break;
        case 0x1010090:
            ns = Android_NS;
            name = "yesNoPreferenceStyle";
            break;
        case 0x10101c1:
            ns = Android_NS;
            name = "zAdjustment";
            break;
        }
        super.attr(ns, name, resourceId, type, obj);
    }

    @Override
    public NodeVisitor child(String ns, String name) {
        if (isEmpty(ns)) {
            ns = null;
        }
        NodeVisitor nv = super.child(ns, name);
        if (nv != null) {
            nv = new FixManifestAdapter(nv);
        }
        return nv;
    }

    @Override
    public void ns(String prefix, String uri, int ln) {
        if (isNullOrEmpty(uri)) {
            return;
        }
        if (isEmpty(prefix)) {
            prefix = null;
        }
        if (Android_NS.equals(uri)) {
            prefix = "android";
        }
        super.ns(prefix, uri, ln);
    }

}
