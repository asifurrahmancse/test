package io.naztech.atmlogservice.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.naztech.atmlogservice.model.AtmLog;
import io.naztech.atmlogservice.scheduler.LogFileProcessScheduler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService {
	@Autowired
	DbService dbService;

	public void readFileDataAndSaveIntoDb(File file) throws FileNotFoundException {

		Scanner scan1 = new Scanner(file);
		Scanner scan = new Scanner(file);

		while (scan1.hasNextLine()) {
			if (scan1.nextLine().contains("|DATE")) {
				scan1.nextLine();
				String firstLine = (scan1.nextLine());
				String ChackLine = firstLine.substring(0, 12);

				while (scan.hasNextLine()) {
					String line = scan.nextLine();
					if (line.startsWith(ChackLine)) {
						line = line.replaceAll(" ", "");

						List<String> rowData = Arrays.asList(line.split("\\|"));
						AtmLog atmLog = setDataIntoModel(rowData,file);//file

						dbService.insertIntoDb(atmLog);
					}
				}
			}
		}
		scan1.close();
		scan.close();
	}

	public File[] readAllFile(String path) {

		File smbFile = new File(path);
		String a[] = smbFile.list();
		File dir = new File(path);
		File[] files = dir.listFiles();

		Arrays.sort(files, new Comparator<File>() {
			public int compare(File f1, File f2) {
				return Long.valueOf(f2.lastModified()).compareTo(f1.lastModified());
			}
		});

    	//File Path2 =  files[0] ;
		
		String Path2 = "" + files[0] + "";

		File dir1 = new File(Path2);
		File[] mainFile = dir1.listFiles();

		return mainFile;
		//return Path2;

	}

	public boolean moveFile(File sourceFile, String desPath) {
		boolean hasMove = false;

		desPath += sourceFile.separator + sourceFile.getName();

		try {
			Path p = Files.copy(Paths.get(sourceFile.getAbsolutePath()), Paths.get(desPath),
					StandardCopyOption.REPLACE_EXISTING);

			if (p.toString().equals(desPath))
				hasMove = true;

		} catch (IOException e) {
			log.error("error {}, \nMessage *** : {}", e, e.getLocalizedMessage());
		}

		return hasMove;
	}

	private AtmLog setDataIntoModel(List<String> rowData, File file) {
		String  fileName = file.getName();
		AtmLog atmLog = new AtmLog();

		atmLog.setPostingDate(rowData.get(1));
		atmLog.setTransactionDate(rowData.get(2));
		atmLog.setCardNo(rowData.get(3));
		atmLog.setAccountNo(rowData.get(4));
		atmLog.setTransactionName(rowData.get(5));
		atmLog.setTerminalId(rowData.get(6));
		atmLog.setTerminalLocation(rowData.get(7));
		atmLog.setRetailerId(rowData.get(8));
		atmLog.setApproval(rowData.get(9));
		atmLog.setMccCode(rowData.get(10));
		atmLog.setPosCondition(rowData.get(11));
		atmLog.setRrn(rowData.get(12));
		atmLog.setStan(rowData.get(13));
		atmLog.setIssuer(rowData.get(14));
		atmLog.setAcquirer(rowData.get(15));
		atmLog.setTxnAmountBdt(rowData.get(16));
		atmLog.setTxnFeeAmountBdt(rowData.get(17));
		atmLog.setVatAmountBdt(rowData.get(18));
		atmLog.setIssuerFeeBd(rowData.get(19));
		atmLog.setIssuerVatBdt(rowData.get(20));
		atmLog.setTotalAmountBdt(rowData.get(21));
		atmLog.setIssuerIncomeBdt(rowData.get(22));
		atmLog.setAcquirerIncomeBdt(rowData.get(23));
		atmLog.setItclIncomeBdt(rowData.get(24));
		atmLog.setBankProfitBdt(rowData.get(25));
		atmLog.setSourceFileData(fileName);

		return atmLog;

	}

}
