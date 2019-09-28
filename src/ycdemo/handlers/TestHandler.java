package ycdemo.handlers;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.teamcenter.rac.aif.AIFDesktop;
import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.kernel.AIFComponentContext;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.common.Activator;
import com.teamcenter.rac.ets.external.DispatcherRequestFactory;
import com.teamcenter.rac.kernel.ServiceData;
import com.teamcenter.rac.kernel.TCAttachmentScope;
import com.teamcenter.rac.kernel.TCAttachmentType;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.rac.kernel.TCComponentBOMWindow;
import com.teamcenter.rac.kernel.TCComponentCfgActivityLine;
import com.teamcenter.rac.kernel.TCComponentDataset;
import com.teamcenter.rac.kernel.TCComponentFolder;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCComponentFormType;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCComponentItemType;
import com.teamcenter.rac.kernel.TCComponentListOfValues;
import com.teamcenter.rac.kernel.TCComponentListOfValuesType;
import com.teamcenter.rac.kernel.TCComponentReleaseStatus;
import com.teamcenter.rac.kernel.TCComponentReleaseStatusType;
import com.teamcenter.rac.kernel.TCComponentRevisionRule;
import com.teamcenter.rac.kernel.TCComponentRevisionRuleType;
import com.teamcenter.rac.kernel.TCComponentTask;
import com.teamcenter.rac.kernel.TCComponentTaskTemplate;
import com.teamcenter.rac.kernel.TCComponentTcFile;
import com.teamcenter.rac.kernel.TCComponentUser;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCProperty;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.kernel.TCUserService;
import com.teamcenter.schemas.core._2010_04.languageinformation.GetLanguagesListInput;
import com.teamcenter.services.internal.rac.core.DispatcherManagementService;
import com.teamcenter.services.internal.rac.core._2008_06.DispatcherManagement;
import com.teamcenter.services.rac.core.DataManagementService;
import com.teamcenter.services.rac.core._2007_01.DataManagement.WhereReferencedResponse;
import com.teamcenter.services.rac.core._2008_06.DataManagement.CreateIn;
import com.teamcenter.services.rac.core._2008_06.DataManagement.CreateOut;
import com.teamcenter.services.rac.core._2008_06.DataManagement.CreateResponse;
import com.teamcenter.services.rac.core._2012_02.DataManagement.WhereUsedConfigParameters;
import com.teamcenter.services.rac.core._2012_02.DataManagement.WhereUsedInputData;
import com.teamcenter.services.rac.core._2012_02.DataManagement.WhereUsedResponse;
import com.teamcenter.services.rac.core._2015_07.DataManagement.CreateIn2;
import com.teamcenter.services.rac.core._2015_07.DataManagement.CreateInput2;

public class TestHandler extends AbstractHandler {
	public int progress = 0;
	

	
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		
		AbstractAIFApplication app=AIFUtility.getCurrentApplication();
		InterfaceAIFComponent[] targets=app.getTargetComponents();
	    TCSession session = (TCSession) AIFUtility.getCurrentApplication().getSession();
	    
	    //1.验证客制化零件
/*	    TCComponentItemRevision itemRev =  (TCComponentItemRevision) targets[0];
	    try {
	    	HashMap<String, TCComponent> hashmap = new TestHandler().CustomItemsKeyValue(itemRev);
	    	for (Map.Entry<String, TCComponent> entry : hashmap.entrySet()) {
				System.out.println(entry.getKey()+" ; "+entry.getValue().getProperty("item_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/

	    
	    	
	    //2.验证调用userservice
/*	    TCComponentBOMLine topBomLine =  (TCComponentBOMLine) targets[0];
        //call user service here
        Object[] objs = new Object[1];
        objs[0] = topBomLine;
        
        try {
            TCUserService userServ = session.getUserService();
            String[] objReturn = (String[]) userServ.call("cust_bom_check_report", objs);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        ICCTUserService icct = new ICCTUserService(session.getSoaConnection());
        try {
        	icct.callMethod("methodName",null,null);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	    
	    //3.判断是否新能源结构
/*	    TCComponentItemRevision itemRev =  (TCComponentItemRevision) targets[0];
	    try {
			TCComponent[] tccomps = itemRev.getRelatedComponents();
			TCComponent[] views = itemRev.getRelatedComponents("view");
			System.out.println();
			System.out.println();
		} catch (TCException e) {
			e.printStackTrace();
		}*/
	    
	    //4.获取Item下最新发布的版本
/*	    TCComponentItem item =  (TCComponentItem) targets[0];
	    
	    try {
			TCComponentItemRevision lastestPublishedRev = getLastestPublishedItemRevision(item);
			System.out.println(" lastestPublishedRev = "+lastestPublishedRev);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	    
	    //5.验证WhereUsed
	    //6TD-1020500-C112
	    TCComponent component = (TCComponent)targets[0];
	    System.out.println(">>>>>>> component Type = "+component.getType());
	    
	    //6.测试新增验证功能
	    
	    System.out.println("测试新增验证功能！");

	    return null;
	}
	
	public TCComponentItemRevision getLastestPublishedItemRevision(TCComponentItem item)throws Exception{
		TCComponentItemRevision[] itemRevsions = item.getReleasedItemRevisions();
		if(itemRevsions==null||itemRevsions.length==0) return null;
		return itemRevsions[0];
	}
	
	public void ProcessBOM(final TCComponentBOMLine product){
		Shell shell = AIFDesktop.getActiveDesktop().getShell();
		IRunnableWithProgress runnable = new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				try {
					try {
						travertBOM(product, monitor);
					} catch (Exception e) {
						e.printStackTrace();
					}
					monitor.done();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		try {
			new ProgressMonitorDialog(shell).run(true, true, runnable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void travertBOM(TCComponentBOMLine product,IProgressMonitor monitor){
		try {
			showProgreessMessage(monitor,"Open.." + product);
			if(product==null) return;
//			TCComponentBOMWindow window = product.window();
			AIFComponentContext[] children = product.getChildren();
			for (AIFComponentContext child : children) {
				TCComponentBOMLine childBomline = (TCComponentBOMLine) child.getComponent();
				travertBOM(childBomline,monitor);
			}
			
			if(product.getItemRevision()==null) return;
			String compType = product.getItemRevision().getType();
			//Y7ProductNodeRevision C7UxoB3hynC0JC  object_name = "K08配置BOM"
			//Y7ModuleUnitRevision jUXxoB3hynC0JC  object_name = "机油集滤器模块"
			if(compType.equalsIgnoreCase("Y7ProductNodeRevision")||compType.equalsIgnoreCase("Y7ModuleUnitRevision")){
				System.err.println(">>>>>> "+product);
				TCComponentItem item = product.getItem();
				TCComponent[] comps = item.getRelatedComponents("Y7SAPPrdList");
				item.remove("Y7SAPPrdList", comps);
//				for (TCComponent comp : comps) {
//					try {
//						comp.delete();
//					} catch (Exception e) {
//						System.out.println(e.getMessage());
//					}
//					
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showProgreessMessage(IProgressMonitor monitor,String messge) {
	  	
		if(progress==500){
			progress=0;
			 monitor.beginTask("Handler", 500);
		}
		progress++;
		monitor.worked(1);
		monitor.subTask(messge);
	}
	
	
	public   HashMap<String, TCComponent> CustomItemsKeyValue(TCComponentItemRevision itemRev)throws Exception{
		//抽象件Key，客制化零件Value
		HashMap<String, TCComponent> hashMap  = new HashMap<String, TCComponent>();hashMap.clear();
		
		//供货代号下客制化零件清单
		TCComponent[] customerItems = itemRev.getRelatedComponents("Y7CustomizedItemList");
		for (TCComponent customerItem : customerItems) {
			String customerItemID = customerItem.getProperty("item_id");
			TCComponent[] abstractItems = customerItem.getRelatedComponents("Y7Source");
			for (TCComponent abstractItem : abstractItems) {
				String abstractItemID = abstractItem.getProperty("item_id");
				hashMap.put(abstractItemID, customerItem);
			}
		}
		return hashMap;
	}
	
	public static int handlerString(String s){
		if(s==null||"".equals(s)||s.equalsIgnoreCase("null")) return 0;
		String handler_s01 = s.replace(" ", "");
		String[] ss = handler_s01.split("&");
		int start = 0;
		int end = 0;
		for (String string : ss) {
			System.out.println(string+" ； "+ string.matches(">="));
			if(string.indexOf(">=")>-1){
				String handler_s02 = string.replace(">=", "");
				start = Integer.parseInt(handler_s02);
			}
			if(string.indexOf("<=")>-1){
				String handler_s02 = string.replace("<=", "");
				end = Integer.parseInt(handler_s02);
			}
		}
		return (start+end)/2;
	}
	
	
	public static void main(String[] args) {
		
		String wuyu = "加机油口=齿轮室有一个加机油口&后端PTO=不带PTO";
		
		String wuyu1 = "后端PTO=不带PTO&加机油口=齿轮室有一个加机油口";
		
		System.out.println("wuyu = "+handlerSortString(wuyu));
		System.out.println("wuyu1 = "+handlerSortString(wuyu1));
	}
	
	public static String handlerSortString(String s){
		String result = "";
		if(s==null||"".equals(s)||s.equalsIgnoreCase("null")) return result;
		
		String[] ss = s.split("&");
		Arrays.sort(ss);
		
		for (String string : ss) {
			result = result +string+"&";
		}
		
		if(result.length()>1) result = result.substring(0, result.length()-1);
		return result;
	}

}
