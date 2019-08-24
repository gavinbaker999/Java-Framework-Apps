		public interface msgQueueProcess {
			public void msgQueuePollStart(String msgQueue);
			public void msgQueuePollStop(String msgQueue);
			
			public void msgSet(String msgQueue,String msg);
			public void msgGet(String msgQueue);
			public void msgDelete(String msgQueue);
			public void msgPollData(String msgQueue,String data);
			public void msgQueueCleanupProcess(String msgQueue);
		}
