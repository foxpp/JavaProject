package com.dp.example;


interface LightAction {
	void next(TrafficLight machine);
}

enum Light implements LightAction {
	RED {
		@Override
		public void next(TrafficLight machine) {
			machine.setState(YELLOW);
		}

	},
	GREEN {
		@Override
		public void next(TrafficLight machine) {
			machine.setState(RED);
		}

	},
	YELLOW {
		@Override
		public void next(TrafficLight machine) {
			machine.setState(GREEN);
		}

	};
}

class Car implements Runnable {
	int length;
	int speed;
	String name;

	public Car(String aName, int aLength, int aSpeed) {
		name = aName;
		length = aLength;
		speed = aSpeed;
	}

	@Override
	public void run() {
		while ((length - speed) >= 0) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			length -= speed;
			StringBuffer buffer = new StringBuffer();
			buffer.append("car: " + name + ", ");
			if (TrafficLight.INSTANCE.now() == Light.GREEN) {
				buffer.append("绿灯，加速");
			} else if (TrafficLight.INSTANCE.now() == Light.YELLOW) {
				buffer.append("黄灯，减速");
			} else {
				buffer.append("红灯，停车");
			}
			System.out.println(buffer + " 剩余距离： " + length);
		}
		System.out.println(name + " 到达终点!");
	}
}

public enum TrafficLight implements Runnable {
	INSTANCE;

	private Light state = Light.RED;

	void setState(Light aState) {
		state = aState;
	}

	Light now() {
		return state;
	}

	void change() {
		state.next(this);
	}

	@Override
	public void run() {

		for (int i = 0; i < 100; i++) {
			try {
				System.out.println("now " + INSTANCE.now());
				Thread.sleep(500);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			INSTANCE.change();

		}
	}

	public static void main(String[] args) {
		Thread trafficThread = new Thread(TrafficLight.INSTANCE);
		trafficThread.start();
		Thread mazda = new Thread(new Car("马自达", 1000, 50));
		mazda.start();
		Thread buick = new Thread(new Car("别克", 1200, 40));
		buick.start();
		Thread qq = new Thread(new Car("qq", 1000, 30));
		qq.start();
	}
}
